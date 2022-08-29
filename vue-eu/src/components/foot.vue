<template>
  <div>
    <div class="clearfix-new">
      <div class="footer-log">
        <a href="/">
          <img src="/static/img/logo.png" alt="">
        </a>
      </div>
      <div style="margin-left: 400px;margin-top: -120px">
        <dl class="footer-dl">
          <dt>关于活动树</dt>
          <dd>
            <a href="/aboutUs?about=1" target="_blank">关于我们</a>
          </dd>
          <dd>
            <a href="/aboutUs?about=2" target="_blank">用户协议</a>
          </dd>
          <dd>
            <a href="/aboutUs?about=3" target="_blank">版权说明</a>
          </dd>
        </dl>
        <dl class="footer-dl">
          <dt>网站服务</dt>
          <dd>
            <a href="/vipBenefit" target="_blank">VIP权益</a>
          </dd>
          <dd>
            <a href="/aboutUs?about=4" target="_blank">帮助中心</a>
          </dd>
          <dd>
            <a href="/aboutUs?about=5" target="_blank">售后服务</a>
          </dd>
        </dl>
        <dl class="footer-dl">
          <dt>常用功能</dt>
          <dd>
            <a href="/plan/list.html?category=0" target="_blank">方案中心</a>
          </dd>
          <dd>
            <a class="needLogin buyCoin" data-url="coinBuy" target="_blank">秀币购买</a>
          </dd>
        </dl>
      </div>

      <div class="code-wrap">
        <div class="code-item">
          <img src="../../static/img/05.jpg" alt="">
          <p>活动树微信公众号</p>
        </div>
        <div class="code-item">
          <img src="../../static/img/06.png" alt="">
          <p>加入社群</p>
        </div>
      </div>
    </div>
    <div class="copyright">
      <p>活动树网站及其文字内容归北京世展智能科技有限公司所有，任何单位及个人未经许可，不得擅自转载使用。本站部分文字及图片来源于网络，如侵犯到您的权益，请及时告知，本站将及时处理或撤换。</p>
      <p>北京世展智能科技有限公司
        <span>|</span>Copyright©1692015 huodongshu.com
        <span>|</span>
        <a style="color: #333;" href="http://www.beian.miit.gov.cn/" target="_blank">京ICP备 14023406号-1</a>
        <span>|</span>北京市朝阳区酒仙桥北路9号恒通国际创新园C9
      </p>
    </div>
  </div>
</template>

<script>
    export default {
        name: "foot"
    }
</script>

<style scoped>
  .footer-log{
    width: 200px;
    height: 200px;
  }
  .footer-log img{
    vertical-align: top;
    width: 126px;
    height: 38px;
    position: relative;
    top: 20px;
    right: -50px;
  }
  .clearfix-new{
    width: 100%;
    height: 200px;
    right: -50px;
    background-color: #fff;
    color: #333;
  }
  .footer-dl{
    margin: 3px 56px 0 0;
    float: left;
    letter-spacing: 2px;
    right: -300px;

  }
  .footer-dl dt{
    font-size: 14px;
    letter-spacing: 2px;
    color: #000000;
    margin-bottom: 13px;
    position: relative;
    right: -40px;
  }
  .footer-dl a{
    font-size: 14px;
    font-weight: normal;
    font-stretch: normal;
    color: #606060;
    line-height: 24px;
    text-decoration: none;
  }
  .code-wrap{
    float: right;
    position: absolute;
    margin-top: 80px;
    margin-left: 1000px;
  }
  .code-item{
    float: right;
    width: 110px;
    height: 50px;
    text-align: center;
    margin-left: 32px;
  }
  .code-item img{
    width: 96px;
    height: 96px;
    position: relative;
    top: -100px;
  }
  .code-item p{
    font-size: 13px;
    line-height: 40px;
    color: #606060;
    position: relative;
    top: -120px;
  }
  .copyright{
    float: left;
    width: 1400px;
    height: 120px;
    right: -50px;
    text-align: center;
    font-size: 12px;
    color: #909090;
  }
</style>
