<template>
    <el-container>
        <!-- 头部 -->
        <el-header height="70px">
            <el-row>
                <el-col :span="20" class="login">
                    <div class="header">
                        <el-avatar src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"></el-avatar>
                        <template class="username">
                            <el-link type="info">用户: </el-link>
                        </template>
                    </div>
                </el-col>
                <el-col class="logout" :span="4">
                    <div class="navOperater">
                        <el-link type="info">退出系统</el-link>
                    </div>
                </el-col>
            </el-row>
        </el-header>
        <el-container style="padding-top:10px">
            <!-- 侧边栏 -->
            <el-aside style="width: 220px">
                <el-menu default-active="2" @open="handleOpen" @close="handleClose" background-color="#545c64" text-color="#fff" active-text-color="#ffd04b" router>
                    <el-submenu index="1">
                        <template slot="title">
                            <i class="el-icon-location"></i>
                            <span>库存管理</span>
                        </template>
                        <el-menu-item-group>
                            <el-menu-item index="commodityInquiry">库存查询</el-menu-item>
                            <el-menu-item index="inAndOutRecords">出入库记录</el-menu-item>
                        </el-menu-item-group>
                    </el-submenu>
                    <el-submenu index="2">
                        <template slot="title">
                            <i class="el-icon-menu"></i>
                            <span slot="title">出入库管理</span>
                        </template>
                        <el-menu-item-group>
                            <el-menu-item index="warehousing">货物入库</el-menu-item>
                            <el-menu-item index="outWarehousing">货物出库</el-menu-item>
                        </el-menu-item-group>
                    </el-submenu>
                    <el-submenu index="3">
                        <template slot="title">
                            <i class="el-icon-document"></i>
                            <span slot="title">人员管理</span>
                        </template>
                        <el-menu-item-group>
                            <el-menu-item index="admin">仓库管理员管理</el-menu-item>
                        </el-menu-item-group>
                    </el-submenu>
                    <el-submenu index="4">
                        <template slot="title">
                            <i class="el-icon-setting"></i>
                            <span slot="title">基础数据</span>
                        </template>
                        <el-menu-item-group>
                            <el-menu-item index="supplierInformation">供应商信息管理</el-menu-item>
                        </el-menu-item-group>
                        <el-menu-item-group>
                            <el-menu-item index="customerInformation">客户信息管理</el-menu-item>
                        </el-menu-item-group>
                        <el-menu-item-group>
                            <el-menu-item index="goodsInformation">货物信息管理</el-menu-item>
                        </el-menu-item-group>
                        <el-menu-item-group>
                            <el-menu-item index="warehouseInformation">仓库信息管理</el-menu-item>
                        </el-menu-item-group>
                    </el-submenu>
                </el-menu>
            </el-aside>
            <!-- 内容区 -->
            <el-main>
                <el-row style="padding: 12px 0px 0px 12px;margin-bottom: 10px;background-color:#EFF2F7;height: 40px;">
                    <template>
                        <el-breadcrumb separator="/">
                            <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
                            <el-breadcrumb-item v-for="(item, index) in $route.meta" :key="index">{{item}}</el-breadcrumb-item>
                        </el-breadcrumb>
                    </template>
                </el-row>
                <keep-alive>
                    <router-view></router-view>
                </keep-alive>
            </el-main>
        </el-container>
    </el-container>
</template>

<script>
// 导入组件

export default {
    name: "home",
    data () {
        return {
            url: "static/img/bj.jpg",
            fits: ["fill", "contain", "cover", "none", "scale-down"]
        };
    },
    methods: {
        handleOpen (key, keyPath) {
            console.log(key, keyPath);
        },
        handleClose (key, keyPath) {
            console.log(key, keyPath);
        },
        navUser () {
            this.$router.push("/admin/admin");
            console.log("ok")
        },
        navPwd () {
            this.$router.push("/admin/pwdReset");
            console.log("no")
        }
    }
};
</script>
<style lang="scss" scoped>
body > .el-container .el-main {
    margin-left: 243px;
    border: solid 1px #dcdfe6;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.12), 0 0 6px rgba(0, 0, 0, 0.04);
    min-height: 600px;
    // overflow: hidden;
}

.el-header {
    background-color: #409eff;
    color: #333;
    text-align: left;
    line-height: 60px;
    .logout {
        .navOperater {
            padding-top: 8px;
            width: 80px;
            height: 80px;
            float: right;
            line-height: 50px;
        }
    }
    .login {
        .header {
            padding-top: 10px;
        }
    }
}
.el-aside {
    background-color: #545c64;
    display: block;
    position: absolute;
    top: 88px;
    bottom: 0px;
    overflow-y: scroll;
}
/* 设置滚动条的样式 */
::-webkit-scrollbar {
    width: 5px;
    background-color: #545c64;
}

/* 滚动槽 */
::-webkit-scrollbar-track {
    border-radius: 2px;
}

/* 滚动条滑块 */
::-webkit-scrollbar-thumb {
    border-radius: 10px;
    background: black;
}
</style>