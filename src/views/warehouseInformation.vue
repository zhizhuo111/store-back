<template>
    <el-form :inline="true" :model="formInline" class="demo-form-inline">
        <el-row>
            <el-col :span="3">
                <el-form-item>
                    <el-select v-model="value" placeholder="查询条件">
                        <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value">
                        </el-option>
                    </el-select>
                </el-form-item>
            </el-col>
            <el-col :span="4">
                <el-form-item>
                    <el-input v-model="formInline.user" placeholder="看是否能和查询条件同步"></el-input>
                </el-form-item>
            </el-col>
            <el-col :span="6">
                <el-form-item>
                    <el-button type="primary" @click="onSubmit">查询</el-button>
                </el-form-item>
            </el-col>
        </el-row>
        <el-row>
            <el-col :span="12">
                <el-button type="primary" @click="onSubmit">添加仓库信息</el-button>
                <el-button type="primary" @click="onSubmit">导入</el-button>
                <el-button type="primary" @click="onSubmit">导出</el-button>
            </el-col>
        </el-row>
        <el-form-item>
            <el-main style="max-height: 380px !important;border:solid 1px #E4E7ED;width:1200px;margin-top: 10px">
                <el-table :data="tableData" style="width: 100%">
                    <el-table-column prop="date" label="仓库ID" width="180">
                    </el-table-column>
                    <el-table-column prop="name" label="仓库地址" width="180">
                    </el-table-column>
                    <el-table-column prop="address" label="仓库管理员">
                    </el-table-column>
                    <el-table-column prop="date" label="状态" width="180">
                    </el-table-column>
                    <el-table-column prop="name" label="操作" width="180">
                    </el-table-column>
                </el-table>
            </el-main>
        </el-form-item>
    </el-form>
</template>
<script>
export default {
    data () {
        return {
            formInline: {
                user: '',
                region: ''
            },
            options: [{
                value: '选项1',
                label: '货物ID'
            }, {
                value: '选项2',
                label: '货物名称'
            }, {
                value: '选项3',
                label: '货物类型'
            }, {
                value: '选项4',
                label: '所有'
            },],
            value: '',
            tableData: [{
                date: '2016-05-02',
                name: '王小虎',
                address: '上海市普陀区金沙江路 1518 弄'
            }, {
                date: '2016-05-04',
                name: '王小虎',
                address: '上海市普陀区金沙江路 1517 弄'
            }, {
                date: '2016-05-01',
                name: '王小虎',
                address: '上海市普陀区金沙江路 1519 弄'
            }, {
                date: '2016-05-03',
                name: '王小虎',
                address: '上海市普陀区金沙江路 1516 弄'
            }]
        }
    },
    methods: {
        onSubmit () {
            console.log('submit!');
        }
    }
}
</script>
<style scoped>
</style>