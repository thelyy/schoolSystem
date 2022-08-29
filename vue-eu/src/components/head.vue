<template>
      <div class="head" style="display: block">
        <div class="head-wrap" >
          <h1 class="h1" style="font-size: 14px;margin-right: 52px;float: left;">
            <a class="logo" href="/">
              <img src="/static/img/logo.png" style="width: 80px;height: auto">
            </a>
          </h1>
          <ul class="nav">
            <li class="nav-item ">
              <a class="jumpPlanList" href="/index" target="_blank">方案中心</a>
            </li>
            <li class="nav-item">
              <a class="jumpContent" href="/" target="_blank">演讲嘉宾</a>
            </li>
            <li class="nav-item">
              <a class="jumpActivity" href="/special/activity.html" target="_blank">活动专题</a>
            </li>
            <li class="nav-item">
              <a class="jumpContent" href="/articlePostList" target="_blank">内容资讯</a>
            </li>
          </ul>
          <div class="head-search">
            <div class="header-content" data-category="0" style="display: block;">
              <input id="headSearchText" class="search-text" type="text" placeholder="每天超过千万人次寻找方案" v-model="keyword" >
              <el-button class="search-btn" type="primary" icon="el-icon-search"
              @click="findGoodsBySearch()">
              </el-button>
            </div>
          </div>
          <div v-if="member == null" class="head-login">
            <a class="header-login needLogin" href="/login">登录</a>
            <a class="header-reg needLogin register" href="/reg">注册</a>
          </div>
          <div v-else class="head-login">
            <el-dropdown>
              <span class="el-dropdown-link">
                <el-image style="height: 25px;width:25px;border-radius: 50%" :src="member.photo"></el-image>
              </span>
              <el-dropdown-menu slot="dropdown">
                <router-link :to="{path: '/user/myinfo', query:{currentIndex: 2}}">
                  <el-dropdown-item icon="el-icon-user-solid">个人信息</el-dropdown-item>
                </router-link>
                <router-link :to="{path: '/user/love', query:{currentIndex: 3}}">
                  <el-dropdown-item icon="el-icon-star-off">我的收藏</el-dropdown-item>
                </router-link>
                <router-link :to="{path: '/user/myhistory', query:{currentIndex: 4}}">
                  <el-dropdown-item icon="el-icon-location-outline">我的足迹</el-dropdown-item>
                </router-link>
                <router-link :to="{path: '/user/order', query:{currentIndex: 5}}">
                  <el-dropdown-item icon="el-icon-location-outline">我的预约</el-dropdown-item>
                </router-link>
                <a @click="logout">
                  <el-dropdown-item icon="el-icon-setting">退出</el-dropdown-item>
                </a>
              </el-dropdown-menu>
              <span style="position: absolute;right: -50px;top: -8px">{{member.nickName}}</span>
            </el-dropdown>
          </div>
        </div>
      </div>
</template>
<script>
import axios from "axios";

export default {
  name: "head",
  data(){
    return{
      member: null,
      goodsList: [], //商品集合
      total:0, //数据总条数
      keyword: '',
    }
  },
  created() {
    axios.get("/eu/findUserByMember",{params:
        {mid: localStorage.getItem("mid")}
    }).then(res=>{
      this.member = res.data.data
    })
  },
  methods:{
    logout(){
      localStorage.removeItem("mid");
      this.$router.push("/login")
    },
    findGoodsBySearch() {
      axios.get("/eu/findGoodsBySearch",{params:{
          gname : this.keyword}
      }).then(res=>{
        this.total = res.data.total;
        this.goodsList = res.data.goodsInfoList
      })
    },
  }
}
</script>

<style scoped>
  .head{
    position: fixed;
    width: 100%;
    height: 56px;
    background: #fff;
    z-index: 109;
    top: 0;
    left: 0;
    right: 0;
  }
  .head-wrap{
    width: 1360px;
    height: 56px;
    line-height: 56px;
    background: #fff;
    margin: 0 auto;
    font-size: 0;
  }
  .head-wrap .nav{
    line-height: 56px;
    height: 56px;
    float: left;
    list-style: none;
  }
  .nav .nav-item{
    margin-left: 30px;
    font-size: 14px;
    position: relative;
    float: left;
    color: #303030;
    list-style: none;
    line-height: 56px;
  }
  .nav-item a{
    display: inline-block;
    text-decoration: none;
    color: #333;
  }
  .head-search{
    width: 472px;
    display: inline-block;
    height: auto;
    vertical-align: middle;
    line-height: 56px;
  }
  .header-content{
    display: inline-block;
    margin-left: 52px;
    height: 32px;
    line-height: 32px;
    width: 420px;
    position: relative;
    vertical-align: middle;
  }
  .head-login{
    display: inline-block;
    vertical-align: middle;
    width: 250px;
    height: 50px;
  }
  .header-login{
    margin: 0 12.8px 0;
    width: 62px;
    height: 24px;
    background-color: #de002d;
    border-radius: 12px;
    font-size: 14px;
    text-align: center;
    display: inline-block;
    color: #fff;
    line-height: 24px;
    position: relative;
    vertical-align: middle;
    text-decoration: none;
  }
  .header-reg{
    line-height: 12px;
    display: inline-block;
    font-size: 14px;
    color: #404040;
    position: relative;
    vertical-align: middle;
    text-decoration: none;
  }
  .search-text{
    width: 244px;
    height: 32px;
    padding-left: 12px;
    font-size: 14px;
    line-height: 32px;
    float: left;
    background-color: #f5f5f5;
    color: #404040;
    border: 0;
    resize: none;
  }
  .search-btn{
    border-radius: 0 4px 4px 0;
    width: 64px;
    height: 32px;
    background-color: #de002d;
    position: relative;
  }
</style>
