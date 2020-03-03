<template>
    <div>
        <el-row>
            <el-image style="width: 400px; height: 200px;padding:50px" :src="url0" :fit="fits"></el-image>
            <el-image style="width: 400px; height: 200px;padding:50px" :src="url" :fit="fits"></el-image>
        </el-row>
        <el-row>
            <el-image style="width: 400px; height: 200px;padding:50px" :src="url1" :fit="fits"></el-image>
            <el-image style="width: 400px; height: 200px;padding:50px" :src="url2" :fit="fits"></el-image>
        </el-row>
    </div>
</template>

<script>
export default {
    data () {
        return {
            fits: ['cover'],
            url0: 'static/img/p4.png',
            url: 'static/img/p1.jpg',
            url1: 'static/img/p2.jpg',
            url2: 'static/img/p3.jpg',
        }
    },
    components: {

    }
}
</script>

<style scoped>
.h1 {
    color: red;
    font-size: 30px;

}
.span {
    color: grey;
    font-size: 30px;
    display: block;
    margin: 0;
    padding-top: 50px
}
</style>
