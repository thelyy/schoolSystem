<template>
  <div id="login">
    <el-form ref="loginFrom" :model="form" :rules="rules" label-width="80px" class="login-box">
      <h3 class="login-title">欢迎登录</h3>
      <el-form-item label="邮箱" prop="email">
        <el-input type="text" placeholder="请输入邮箱" v-model="form.email"></el-input>
      </el-form-item>
      <el-form-item label="密码" prop="pwd">
        <el-input type="pwd" placeholder="请输入密码" v-model="form.pwd"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" v-on:click="loginMember('loginFrom')">登录</el-button>
        <el-button type="primary" @click="reg()">注册</el-button>
      </el-form-item>
      <div style="width:100%;text-align:center">
        <span @click="updatepwd()" >忘记密码？</span>
      </div>
    </el-form>

    <el-dialog
      title="温馨提示"
      :visible.sync="dialogVisible"
      width="30%"
      :before-close="handleClose">
      <span>请输入账号和密码</span>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="dialogVisible = false">确定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
  import axios from "axios"
  export default {
    name: "Login",
    data() {
      return {
        form: {
          email: '',
          pwd: '',
        },

        //表单验证
        rules: {
          email: [
            {required: true, message: '账号不可为空', trigger: 'blur'}
          ],
          pwd: [
            {required: true, message: '密码不可为空', trigger: 'blur'}
          ]
        },

        //对话显示和隐藏
        dialogVisible: false
      }
    },
    methods: {
      loginMember(loginFrom){
        axios.get("/eu/loginMember", {
          params:{email:this.form.email, pwd: this.form.pwd}})
          .then(res=>{
            console.log(res.data)
            if (res.data.code == 200){
              localStorage.setItem("mid", res.data.data.mid)
              alert("登录成功！")
              this.$router.push("/index");
            }else{
              alert("登录失败！")
            }
        })
      },
      updatepwd(){
        this.$router.push("/updatepwd");
      },
      reg() {
        this.$router.push("/reg");
        // axios.get("/eu/mail/login").then(res=>{
        //
        // })
      }

    }
  }
</script>

<style lang="scss" scoped>
  .login-box {
    border: 1px solid #DCDFE6;
    width: 350px;
    margin: 180px auto;
    padding: 35px 35px 15px 35px;
    border-radius: 5px;
    -webkit-border-radius: 5px;
    -moz-border-radius: 5px;
    box-shadow: 0 0 25px #909399;
  }

  .login-title {
    text-align: center;
    margin: 0 auto 40px auto;
    color: #303133;
  }
  #login{
    background:url("../../static/img/loginback.jpg");
    width:100%;
    height:100%;
    position:fixed;
    background-size:100% 100%;
  }
  .login-box{
    background-color: rgba(255,255,255,0.7);
  }

</style>
