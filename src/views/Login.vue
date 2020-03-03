<template>
    <div class="container">
        <el-form ref="form" :model="form" label-width="80px" class="login-page" :rules="rules">
            <h2 style="text-align:center">登录</h2>
            <el-form-item label="用  户:" prop="username">
                <el-input v-model="form.username" placeholder="请输入用户名">
                    <el-button slot="prepend" icon="el-icon-user"></el-button>
                </el-input>
            </el-form-item>
            <el-form-item label="密 码:">
                <el-input v-model="form.password" type="password" placeholder="请输入密码">
                    <el-button slot="prepend" icon="el-icon-key"></el-button>
                </el-input>
            </el-form-item>
            <el-button type="primary" icon="el-icon-user-solid" @click="login">登 录</el-button>
        </el-form>
    </div>
</template>
<script>
import Home from '@/views/Home.vue';
export default {
    data () {
        return {
            form: {
                username: "",
                password: ""
            },
            rules: {
                username: [{ required: true, message: "请输入用户名", trigger: "blur" }]
            }
        };
    },
    components:{
        Home
    },
    methods: {
        login () {
            // 数据校验
            this.$refs.form.validate(valid => {
                if (valid) {
                    console.log('判断用户名密码是否正确');
                    this.$router.push({path:'/home'});
                }
            });
        }
    }
}

</script>
<style scoped>
.container {
    margin-top: 200px;
    width: 100%;
    height: 100%;
}
.login-page {
    border-radius: 5px;
    margin: auto;
    width: 350px;
    padding: 35px 55px 15px;
    background: #fff;
    border: 1px solid #eaeaea;
    box-shadow: 0 0 25px #cac6c6;
}
</style>