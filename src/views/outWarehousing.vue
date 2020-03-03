<template>
    <el-form :inline="true" :model="formInline" class="demo-form-inline">
        <el-row>
            <el-col :span="6">
                <el-form-item label="客户" label-width="68px">
                    <el-input style="width:220px" v-model="formInline.user" placeholder="请输入客户名称"></el-input>
                </el-form-item>
            </el-col>
            <el-col :span="6">
                <el-form-item label="出库货物">
                    <el-input v-model="formInline.user" placeholder="请输入入库货物名称"></el-input>
                </el-form-item>
            </el-col>
            <el-col :span="10">
                <el-button type="text" @click="dialogFormVisible = true">详情信息</el-button>
            </el-col>
            <el-dialog title="收货地址" :visible.sync="dialogFormVisible">
                <el-form :model="form">
                    <el-form-item label="活动名称" :label-width="formLabelWidth">
                        <el-input v-model="form.name" autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="活动区域" :label-width="formLabelWidth">
                        <el-select v-model="form.region" placeholder="请选择活动区域">
                            <el-option label="区域一" value="shanghai"></el-option>
                            <el-option label="区域二" value="beijing"></el-option>
                        </el-select>
                    </el-form-item>
                </el-form>
                <div slot="footer" class="dialog-footer">
                    <el-button @click="dialogFormVisible = false">取 消</el-button>
                    <el-button type="primary" @click="dialogFormVisible = false">确 定</el-button>
                </div>
            </el-dialog>
        </el-row>
        <el-row>
            <el-col :span="6">
                <el-form-item label="出库仓库">
                    <el-select v-model="value" placeholder="请选择仓库">
                        <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value">
                        </el-option>
                    </el-select>
                </el-form-item>
            </el-col>
            <el-col :span="6">
                <el-form-item label="出库数量">
                    <el-input v-model="formInline.user" placeholder="请输入数量"></el-input>
                </el-form-item>
            </el-col>
            <el-col :span="6">
                <el-form-item>
                    <template>当前库存：{{}}</template>
                </el-form-item>
            </el-col>
        </el-row>
        <el-footer style="background-color:#EFF2F7">
            <el-button style="margin-left:1000px;margin-top:10px" type="primary">提交出库</el-button>
        </el-footer>
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
            dialogTableVisible: false,
            dialogFormVisible: false,
            form: {
                name: '',
                region: '',
                date1: '',
                date2: '',
                delivery: false,
                type: [],
                resource: '',
                desc: ''
            },
            formLabelWidth: '120px',
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

<style >
</style>
