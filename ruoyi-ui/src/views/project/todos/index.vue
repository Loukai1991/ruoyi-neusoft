<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">

      <el-form-item label="责任人" prop="assignee" v-if="isAdmin">
        <el-select v-model="queryParams.assignee" placeholder="请选择责任人" clearable size="small">
          <el-option label="全部" :value="null">

          </el-option>
          <el-option
            v-for="dict in assigneeOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="截止时间" prop="dealline">
        <el-date-picker clearable size="small" style="width: 200px"
          v-model="queryParams.dealline"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择截止时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="优先级" prop="priority">
        <el-select v-model="queryParams.priority" placeholder="请选择优先级" clearable size="small">
          <el-option
            v-for="dict in priorityOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="项目" prop="pjtId">
        <el-select v-model="queryParams.pjtId" placeholder="请选择所属项目id" clearable size="small">
          <el-option
            v-for="dict in pjtIdOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="产品" prop="productId">
        <el-select v-model="queryParams.productId" placeholder="请选择所属平台产品id" clearable size="small">
          <el-option
            v-for="dict in productIdOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="任务名称" prop="todoTitle">
        <el-input
          v-model="queryParams.todoTitle"
          placeholder="请输入任务标题"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="任务状态" prop="todoStatus">
        <el-select v-model="queryParams.todoStatus" placeholder="请选择状态，0未完成，1进行中，2已完成，3已删除" clearable size="small">
          <el-option
            v-for="dict in todoStatusOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="任务类型" prop="todoType">
        <el-select v-model="queryParams.todoType" placeholder="请选择任务类型" clearable size="small">
          <el-option
            v-for="dict in todoTypeOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="cyan" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row v-if="isAdmin" :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['project:todos:add']"
        >新增</el-button>
      </el-col>
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="success"-->
<!--          icon="el-icon-edit"-->
<!--          size="mini"-->
<!--          :disabled="single"-->
<!--          @click="handleUpdate"-->
<!--          v-hasPermi="['project:todos:edit']"-->
<!--        >修改</el-button>-->
<!--      </el-col>-->
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['project:todos:remove']"
        >删除</el-button>
      </el-col>
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="warning"-->
<!--          icon="el-icon-download"-->
<!--          size="mini"-->
<!--          @click="handleExport"-->
<!--          v-hasPermi="['project:todos:export']"-->
<!--        >导出</el-button>-->
<!--      </el-col>-->
	  <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="todosList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
<!--      <el-table-column label="序号" align="center" prop="todoId" />-->
<!--      <el-table-column label="项目" align="center" prop="pjtId" :formatter="pjtIdFormat"/>-->

      <el-table-column label="产品" align="center" prop="productId" :formatter="productIdFormat" />
      <el-table-column label="任务名称" align="center" prop="todoTitle" />
      <el-table-column label="责任人" align="center" prop="assignee" :formatter="assigneeFormat" />
      <el-table-column label="截止时间" align="center" prop="dealline" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.dealline, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
<!--      <el-table-column label="优先级" align="center" prop="priority" :formatter="priorityFormat"></el-table-column>-->
<!--      <el-table-column label="标签id" align="center" prop="tagId" />-->

<!--      <el-table-column label="任务描述" align="center" prop="todoDesc" />-->
<!--      <el-table-column label="上传附件的id集合" align="center" prop="fileIds" />-->
<!--      <el-table-column label="子任务集合" align="center" prop="childTodoIds" />-->
<!--      <el-table-column label="删除人id" align="center" prop="deleteBy" />-->
<!--      <el-table-column label="删除人" align="center" prop="deleteTime" />-->
      <el-table-column label="任务状态" align="center" prop="todoStatus" :formatter="todoStatusFormat" />
<!--      <el-table-column label="系统提醒时间设定" align="center" prop="remindTime" />-->
<!--      <el-table-column label="1有星标置顶，0无星标置顶" align="center" prop="hasStar" />-->
<!--      <el-table-column label="是否删除" align="center" prop="isDel" />-->
      <el-table-column label="任务类型" align="center" prop="todoType" :formatter="todoTypeFormat" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['project:todos:edit']"
          >修改</el-button>
          <el-button
            v-if="isAdmin"
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['project:todos:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改任务对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="780px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="任务名称" prop="todoTitle">
              <el-input v-model="form.todoTitle" placeholder="请输入任务名称" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="任务描述" prop="todoDesc">
              <el-input v-model="form.todoDesc" placeholder="请输入任务描述" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="责任人" prop="assignee">
              <el-select v-model="form.assignee" placeholder="请选择责任人">
                <el-option
                  v-for="dict in assigneeOptions"
                  :key="dict.dictValue"
                  :label="dict.dictLabel"
                  :value="dict.dictValue"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="截止时间" prop="dealline">
              <el-date-picker clearable size="small" style="width: 200px"
                              v-model="form.dealline"
                              type="date"
                              value-format="yyyy-MM-dd"
                              placeholder="选择截止时间">
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="12">

            <el-form-item label="优先级" prop="priority">
              <el-select v-model="form.priority" placeholder="请选择优先级">
                <el-option
                  v-for="dict in priorityOptions"
                  :key="dict.dictValue"
                  :label="dict.dictLabel"
                  :value="dict.dictValue"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="项目" prop="pjtId">
              <el-select v-model="form.pjtId" placeholder="项目">
                <el-option
                  v-for="dict in pjtIdOptions"
                  :key="dict.dictValue"
                  :label="dict.dictLabel"
                  :value="dict.dictValue"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="产品" prop="productId">
              <el-select v-model="form.productId" placeholder="产品">
                <el-option
                  v-for="dict in productIdOptions"
                  :key="dict.dictValue"
                  :label="dict.dictLabel"
                  :value="dict.dictValue"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="状态" prop="todoStatus">
              <el-select v-model="form.todoStatus" placeholder="请选择状态，0未完成，1进行中，2已完成，3已删除">
                <el-option
                  v-for="dict in todoStatusOptions"
                  :key="dict.dictValue"
                  :label="dict.dictLabel"
                  :value="dict.dictValue"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="任务类型" prop="todoType">
              <el-select v-model="form.todoType" placeholder="请选择任务类型">
                <el-option
                  v-for="dict in todoTypeOptions"
                  :key="dict.dictValue"
                  :label="dict.dictLabel"
                  :value="dict.dictValue"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>


<!--        <el-form-item label="标签id" prop="tagId">-->
<!--          <el-select v-model="form.tagId" placeholder="请选择标签id">-->
<!--            <el-option label="请选择字典生成" value="" />-->
<!--          </el-select>-->
<!--        </el-form-item>-->


<!--        <el-form-item label="上传附件的id集合" prop="fileIds">-->
<!--          <el-input v-model="form.fileIds" placeholder="请输入上传附件的id集合" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="子任务集合" prop="childTodoIds">-->
<!--          <el-input v-model="form.childTodoIds" placeholder="请输入子任务集合" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="删除人id" prop="deleteBy">-->
<!--          <el-input v-model="form.deleteBy" placeholder="请输入删除人id" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="删除人" prop="deleteTime">-->
<!--          <el-input v-model="form.deleteTime" placeholder="请输入删除人" />-->
<!--        </el-form-item>-->

<!--        <el-form-item label="系统提醒时间设定" prop="remindTime">-->
<!--          <el-input v-model="form.remindTime" placeholder="请输入系统提醒时间设定" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="1有星标置顶，0无星标置顶" prop="hasStar">-->
<!--          <el-input v-model="form.hasStar" placeholder="请输入1有星标置顶，0无星标置顶" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="是否删除" prop="isDel">-->
<!--          <el-input v-model="form.isDel" placeholder="请输入是否删除" />-->
<!--        </el-form-item>-->

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listTodos, getTodos, delTodos, addTodos, updateTodos, exportTodos } from "@/api/project/todos";

export default {
  name: "Todos",
  components: {
  },
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 任务表格数据
      todosList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 责任人ID字典
      assigneeOptions: [],
      // 优先级字典
      priorityOptions: [],
      // 所属项目id字典
      pjtIdOptions: [],
      // 所属平台产品id字典
      productIdOptions: [],
      // 状态，0未完成，1进行中，2已完成，3已删除字典
      todoStatusOptions: [],
      // 任务类型字典
      todoTypeOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        assignee: null,
        dealline: null,
        priority: null,
        pjtId: null,
        productId: null,
        todoTitle: null,
        todoStatus: null,
        todoType: null
      },
      // 表单参数
      form: {},
      isAdmin: true,
      // 表单校验
      rules: {
        assignee: [
          { required: true, message: "责任人ID不能为空", trigger: "change" }
        ],
        dealline: [
          { required: true, message: "截止时间不能为空", trigger: "blur" }
        ],
        priority: [
          { required: true, message: "优先级不能为空", trigger: "change" }
        ],
        productId: [
          { required: true, message: "所属平台产品id不能为空", trigger: "change" }
        ],
        todoTitle: [
          { required: true, message: "任务标题不能为空", trigger: "blur" }
        ],
        todoDesc: [
          { required: true, message: "任务描述不能为空", trigger: "blur" }
        ],
        fileIds: [
          { required: true, message: "上传附件的id集合不能为空", trigger: "blur" }
        ],
        createTime: [
          { required: true, message: "创建任务时间不能为空", trigger: "blur" }
        ],
        updateTime: [
          { required: true, message: "更新任务时间不能为空", trigger: "blur" }
        ],
        createBy: [
          { required: true, message: "创建人id不能为空", trigger: "blur" }
        ],
        updateBy: [
          { required: true, message: "更新人id不能为空", trigger: "blur" }
        ],
        deleteBy: [
          { required: true, message: "删除人id不能为空", trigger: "blur" }
        ],
        deleteTime: [
          { required: true, message: "删除人不能为空", trigger: "blur" }
        ],
        todoStatus: [
          { required: true, message: "状态，0未完成，1进行中，2已完成，3已删除不能为空", trigger: "change" }
        ],
        remindTime: [
          { required: true, message: "系统提醒时间设定不能为空", trigger: "blur" }
        ],
        hasStar: [
          { required: true, message: "1有星标置顶，0无星标置顶不能为空", trigger: "blur" }
        ],
        isDel: [
          { required: true, message: "是否删除不能为空", trigger: "blur" }
        ],
        todoType: [
          { required: true, message: "任务类型不能为空", trigger: "change" }
        ]
      }
    };
  },
  created() {
    let params = {}
    if(this.$store.getters.roles[0] === 'common'){
       params = {'assignee':this.$store.getters.userId}
       this.isAdmin = false
    }
    this.getList(params);
    this.getDicts("assigner").then(response => {
      this.assigneeOptions = response.data;
      console.log(this.assigneeOptions)
    });
    this.getDicts("todo_priority").then(response => {
      this.priorityOptions = response.data;
    });
    this.getDicts("project_name").then(response => {
      this.pjtIdOptions = response.data;
    });
    this.getDicts("product_type").then(response => {
      this.productIdOptions = response.data;
    });
    this.getDicts("todo_status").then(response => {
      this.todoStatusOptions = response.data;
    });
    this.getDicts("todo_type").then(response => {
      this.todoTypeOptions = response.data;
    });
  },
  methods: {
    /** 查询任务列表 */
    getList(params) {
      this.loading = true;
      listTodos(Object.assign(this.queryParams,params)).then(response => {
        this.todosList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 责任人ID字典翻译
    assigneeFormat(row, column) {
      return this.selectDictLabel(this.assigneeOptions, row.assignee);
    },
    // 优先级字典翻译
    priorityFormat(row, column) {
      return this.selectDictLabel(this.priorityOptions, row.priority);
    },
    // 所属项目id字典翻译
    pjtIdFormat(row, column) {
      return this.selectDictLabel(this.pjtIdOptions, row.pjtId);
    },
    // 所属平台产品id字典翻译
    productIdFormat(row, column) {
      return this.selectDictLabel(this.productIdOptions, row.productId);
    },
    // 状态，0未完成，1进行中，2已完成，3已删除字典翻译
    todoStatusFormat(row, column) {
      return this.selectDictLabel(this.todoStatusOptions, row.todoStatus);
    },
    // 任务类型字典翻译
    todoTypeFormat(row, column) {
      return this.selectDictLabel(this.todoTypeOptions, row.todoType);
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        todoId: null,
        assignee: null,
        dealline: null,
        priority: null,
        pjtId: null,
        productId: null,
        todoTitle: null,
        todoDesc: null,
        fileIds: null,
        createTime: null,
        updateTime: null,
        createBy: null,
        updateBy: null,
        deleteBy: null,
        deleteTime: null,
        todoStatus: null,
        remindTime: null,
        hasStar: null,
        isDel: null,
        todoType: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.todoId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加任务";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const todoId = row.todoId || this.ids
      getTodos(todoId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改任务";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        let userId = this.$store.getters.userId;
        if (valid) {
          if (this.form.todoId != null) {
            this.form.createBy = userId
            updateTodos(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            this.form.updateBy = userId
            addTodos(this.form).then(response => {
              this.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const todoIds = row.todoId || this.ids;
      this.$confirm('是否确认删除任务编号为"' + todoIds + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delTodos({todoId:todoIds});
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有任务数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportTodos(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
    }
  }
};
</script>
