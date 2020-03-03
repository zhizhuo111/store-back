import Vue from 'vue';
import Router from 'vue-router';
Vue.use(Router)
// 登录
const login = r => require.ensure([], () => r(require('@/views/Login')), 'login');
// 主页
const home = r => require.ensure([], () => r(require('@/views/Home')), 'home');
// 库存管理
const commodityInquiry = r => require.ensure([], () => r(require('@/views/commodityInquiry')), 'commodityInquiry');
const inAndOutRecords = r => require.ensure([], () => r(require('@/views/inAndOutRecords')), 'inAndOutRecords');
// 出入库管理
const warehousing = r => require.ensure([], () => r(require('@/views/Warehousing')), 'warehousing');
const outWarehousing = r => require.ensure([], () => r(require('@/views/outWarehousing')), 'outWarehousing');
// 人员管理
const admin = r => require.ensure([], () => r(require('@/views/Admin')), 'admin');
// 基础数据
const supplierInformation = r => require.ensure([], () => r(require('@/views/supplierInformation')), 'supplierInformation');
const customerInformation = r => require.ensure([], () => r(require('@/views/customerInformation')), 'customerInformation');
const goodsInformation = r => require.ensure([], () => r(require('@/views/goodsInformation')), 'goodsInformation');
const warehouseInformation = r => require.ensure([], () => r(require('@/views/warehouseInformation')), 'warehouseInformation');
// 首页
const index = r => require.ensure([], () => r(require('@/views/Index')), 'index');


// 路由实例对象

export default new Router({
    routes: [
        {
            path: '/',
            component: login
        },
        {
            path: '/home',
            component: home,
            name: '',
            children: [
                {
                    path: '',
                    component: index,
                    meta: [],
                },
                {
                    path: '/commodityInquiry',
                    component: commodityInquiry,
                    meta: ['库存管理', '库存查询'],
                },
                {
                    path: '/inAndOutRecords',
                    component: inAndOutRecords,
                    meta: ['库存管理', '出入库记录'],
                },
                {
                    path: '/warehousing',
                    component: warehousing,
                    meta: ['出入库管理', '货物入库'],
                },
                {
                    path: '/outWarehousing',
                    component: outWarehousing,
                    meta: ['出入库管理', '货物出库'],
                },
                {
                    path: '/admin',
                    component: admin,
                    meta: ['人员管理', '仓库管理员管理'],
                },
                {
                    path: '/supplierInformation',
                    component: supplierInformation,
                    meta: ['基础数据', '供应商信息管理'],
                },
                {
                    path: '/customerInformation',
                    component: customerInformation,
                    meta: ['基础数据', '客户信息管理'],
                },
                {
                    path: '/goodsInformation',
                    component: goodsInformation,
                    meta: ['基础数据', '货物信息管理'],
                },
                {
                    path: '/warehouseInformation',
                    component: warehouseInformation,
                    meta: ['基础数据', '仓库信息管理'],
                },
            ]
        }
    ]
})
