<template>
  <div style="padding: 20px">
    <el-input v-model="name" placeholder="请输入名称" style="width: 300px" clearable></el-input>
    <el-button type="primary" size="default" style="margin-left: 5px" @click="load">查询数据</el-button>
    
    
    <el-button type="primary" @click="handleAdd">新增数据</el-button>
    <el-button type="primary" @click="reset">重启数据</el-button>


    <div>      
        <el-table :data="state.tableData" stripe style="width: 100%">
          <el-table-column prop="date" label="日期" width="180" align="center" />
          <el-table-column prop="name" label="姓名" width="180" align="center" />
          <el-table-column prop="address" label="地址" width="480" align="center"/>
          <el-table-column prop="userNum" label="编号" width="180" align="center"/>
          <el-table-column label="操作">
            <template #default="scope">
              <el-button size="small" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
              <el-button size="small" type="danger" @click="handleDelete(scope.$index, scope.row.id)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
    </div>
    <div style="margin-top: 10px;">
      <el-pagination background layout="prev, pager, next, total, sizes" 
                    :total="state.total"  
                    v-model:current-page="state.pageNum"
                    v-model:page-size="state.pageSize"
                    :page-sizes="[2, 3, 5]"
                    @size-change="load"
                    @current-change="load" />
    </div>

    <!-- Form -->

    <el-dialog v-model="dialogFormVisible" title="信息" width="30%">
      <el-form :model="state.form" label-width="100px" style="padding-right: 30px">
        <el-form-item label="日期" >
          <el-date-picker
                v-model="state.form.date"
                type="date"
                placeholder="选择日期"
                format="YYYY-MM-DD"
                value-format="YYYY-MM-DD"
              />        
          </el-form-item>
        <el-form-item label="姓名">
          <el-input v-model="state.form.name" autocomplete="off" />
        </el-form-item>
        <el-form-item label="地址">
          <el-input v-model="state.form.address" autocomplete="off" />
        </el-form-item>
        <el-form-item label="编号">
          <el-input v-model="state.form.userNum" autocomplete="off" />
        </el-form-item>

      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取消</el-button>
          <el-button type="primary" @click="save">确认</el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>

<style>
.main {
  color: green;
}
</style>


<script setup>
import {ref, reactive} from "vue";
import request from "../utils/request";
import { ElMessage } from 'element-plus'

const dialogFormVisible = ref(false)

//全局保存的编辑行号
let gloablRowIndex = ref(-1)

const name = ref('')

//新增数据 设置新的空的绑值对象 打开弹窗
const handleAdd = () => {
  dialogFormVisible.value = true //打开弹窗
  state.form = {}
}

const handleEdit = (index, row) => {
  console.log(index, row)
  const newOjb = Object.assign({}, row)
  state.form = newOjb
  dialogFormVisible.value = true //打开弹窗
  gloablRowIndex.value = index
}

const reset = () => {
  name.value = ''
  load()
}

const load = () => {
  let offset = (state.pageNum - 1) * state.pageSize
  request.get('user/page/?name=' + name.value + '&offset='+ offset +'&pageSize=' + state.pageSize).then(res => {
    console.log(res)
    state.tableData = res.data
  })

  request.get('user/count/?name=' + name.value).then(res => {
    console.log(res.data)
    state.total = res.data
  })
}

//数据插入table并刷新页面数据，让弹窗关闭
const save = () => {
  // request.post('/user/save', state.form).then(res => {
  //   if (res.code == "200") {
  //     ElMessage.success("添加成功")
  //     dialogFormVisible.value = false
  //   } else {
  //     ElMessage.error(res.msg)
  //   }
  // })

  request({
    method: state.form.id ? 'PUT' : 'POST',
    url: state.form.id ? '/user/update' : '/user/save',
    data: state.form
  }).then(res => {
    if (res.code == "200") {
      ElMessage.success("添加成功")
      dialogFormVisible.value = false
      load() //更新数据
    } else {
      ElMessage.error(res.msg)
    }
  })  
}

const handleDelete = (index, id) => {
  request.delete('/user/del?id=' + id).then(res => {
    if (res.code == "200") {
      ElMessage.success("删除成功")
      load() //更新数据
    } else {
      ElMessage.error(res.msg)
    }
  })  
}


const state = reactive({
  tableData: [
  
  ],

  form: {},
  total: 0,
  pageSize: 2,
  pageNum: 1,
})

load()
</script>