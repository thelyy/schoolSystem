<template>
  <div id="updatepwd">
    <el-form ref="loginFrom" :model="form" :rules="rules" label-width="80px" class="login-box">
      <h3 class="login-title">修改密码</h3>
      <el-form-item label="邮箱" prop="email">
        <el-input type="text" placeholder="请输入邮箱" v-model="form.email"></el-input>
      </el-form-item>
      <el-form-item label="验证码"  prop="code">
        <el-input type="code" prop="code"  placeholder="请输入验证码" v-model="form.code"
                  style="position: relative;width: 140px"></el-input>
      </el-form-item>
      <el-button :disabled="!show" style="position: relative; right: -220px; top: -62px; width: 130px; background-color:#f8f8ff " plain  @click="getCode">
        <span v-show="show">发送验证码</span>
        <span v-show="!show" class="count">{{count}}s后可点击重发</span>
      </el-button>
      <el-form-item label="密码" prop="pwd" style="position: relative;top: -40px;">
        <el-input type="pwd" placeholder="请输入密码" v-model="form.pwd"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" v-on:click="updatepwd('loginFrom')">修改</el-button>
        <el-button type="primary" @click="login()">登录</el-button>
      </el-form-item>
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
  const TIME_COUNT = 10// 设置一个全局的倒计时的时间
  export default {
    name: "updatepwd",
    data() {
      // 邮箱校验
      let validateMobile = (rule, value, callback) => {
        if (!value) {
          callback(new Error('邮箱不可为空'))
        } else {
          if (value !== '') {
            let reg = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/
            if (!reg.test(value)) {
              callback(new Error('请输入格式正确有效的邮箱号'))
            }
          }
          callback()
        }
      }
      // 验证码校验
      let validateCheckCode = (rule, value, callback) => {
        if (!value) {
          callback(new Error('验证码不可为空'))
        } else {
          if (value !== '') {
            let reg = /^[0-9]{6}$/
            if (!reg.test(value)) {
              callback(new Error('请输入收到的6位随机验证码'))
            }
          }
          callback()
        }
      }
      return {
        form: {
          email: '',
          code:'',
          pwd: '',
        },
        show: true,
        count: '',
        timer: null,
        //表单验证
        rules: {
          email: [
            {validator: validateMobile, trigger: 'blur' }
          ],
          code: [
            {validator: validateCheckCode, trigger: 'blur' }
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
      log(forName) {
        //为表单绑定验证功能
        this.$refs[forName].validate((valid) => {
          if (valid) {
            this.$router.push("/main");
          } else {
            this.dialogVisible = true;
            return false;
          }
        });
      },
      updatepwd(){
        axios.get("/eu/updatepwd", {
          params:{email:this.form.email, pwd:this.form.pwd, code:this.form.code}})
          .then(res=>{
            if(this.form.email=="" || this.form.pwd=="" || this.form.code==""){
              alert("请填写完整的信息！")
              return;
            }
            if (res.data.code == 1001) {
              alert("验证码失效！")
              return;
            }else if(res.data.code == 1002){
              alert("该验证码不正确！")
              return;
            }else{
              alert("修改密码成功！")
              this.$router.push("/login");
            }
          })
      },
      getCode () {
        let _this = this
        if (this.form.email === '') {
          _this.$message.error('请先输入邮箱再点击获取验证码');
          return;
        }
        let reg = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/
        if (!reg.test(this.form.email)) {
          _this.$message.error('请输入格式正确有效的邮箱号')
          return;
        }
        axios({
          method: 'post',
          url: '/eu/mail/getupdatepwdCode',
          data: {
            'email': this.form.email,
            'code':  this.form.code,
            'pwd' : this.form.pwd
          }
        }).then(function (res) {
          if (res.data.code == 200) {
            alert("邮箱发送成功！")
          } else if (res.data.code == 1001) {
            alert(res.data.msg)
          } else if(res.data.code = 1003){
            alert("该邮箱不存在！")
          }else{
            alert("邮箱发送异常！")
          }
        })

        // 验证码倒计时
        if (!this.timer) {
          this.count = TIME_COUNT
          this.show = false
          this.timer = setInterval(() => {
            if (this.count > 0 && this.count <= TIME_COUNT) {
              this.count--
            } else {
              this.show = true
              clearInterval(this.timer)
              this.timer = null
            }
          }, 1000)
        }
      },
      login() {
        this.$router.push("/login");
        // axios.get("/eu/mail/login").then(res=>{
        //
        // })
      },
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
  #updatepwd{
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
