<template>
    <div class="home">
      <!-- 顶部导航 -->
      <div class="header">
        <div class="logo">
          <img src="@/assets/images/index1.png" alt="公司logo">
          <div class="company-name">
            <h1>温州壹号翼科技有限公司</h1>
            <h2>WENZHOU EAGLE ONE TECHNOLOGY CO.,LTD</h2>
          </div>
        </div>

      </div>

      <!-- 主要内容区 -->
      <div class="main-content">
        <!-- banner区域 -->
        <div class="banner">
          <img src="@/assets/images/index1.png" alt="无人机">
          <div class="banner-text">
            <h3>培训无人机CAAC执照、AOPA合格证等</h3>
            <p>我们秉持创新敬畏、乐学谦卑、开放发展的价值观，</p>
            <p>致力于打造一个有产品、有技术、有服务、有案例的共享平台。</p>
          </div>
        </div>

        <!-- 公司简介 -->
        <div class="company-profile">
          <h2>公司简介</h2>
          <h3>COMPANY PROFILE</h3>
          <div class="profile-content">
            <p>温州壹号翼科技有限公司是一家专注于无人机专业培训、技术研究和服务的高新技术企业。公司以"让空域更安全，让飞行更美好"为愿景，以"让科技插上安全的翅膀"为使命，秉持创新敬畏、乐学谦卑、开放发展的价值观，致力于打造一个有产品、有技术、有服务、有案例的共享平台。</p>
          </div>
        </div>

        <!-- 培训中心展示 -->
        <div class="training-center">
          <img src="@/assets/images/index2.png" alt="培训中心">
          <h2>壹号翼无人机驾驶员培训中心 EAGLE ONE</h2>
        </div>

        <!-- 无人机展示 -->
        <div class="drone-display">
          <h2>训练无人机展示（部分）</h2>
          <h3>DRONE DISPLAY</h3>
          <div class="display-items">
            <div class="display-item">
              <img src="@/assets/images/index3.png" alt="超视距训练机">
              <p>超视距训练机</p>
            </div>
            <div class="display-item">
              <img src="@/assets/images/index4.png" alt="视距内训练机">
              <p>视距内训练机</p>
            </div>
            <div class="display-item">
              <img src="@/assets/images/index5.png" alt="同步考试电子训练系统">
              <p>同步考试电子训练系统</p>
            </div>
          </div>
        </div>
      </div>
      <!-- <div class="contact section">
        <h2>联系我们</h2>
        <h3>CONTACT US</h3>
        <div class="contact-info">
          <div class="info-item">
            <i class="icon-location"></i>
            <p>地址：温州市XX区XX路XX号</p>
          </div>
          <div class="info-item">
            <i class="icon-phone"></i>
            <p>电话：0577-XXXXXXXX</p>
          </div>
          <div class="info-item">
            <i class="icon-email"></i>
            <p>邮箱：contact@eagleone.com</p>
          </div>
        </div>
      </div> -->
      <div class="footer">
      <p>© 2024 温州壹号翼科技有限公司 版权所有</p>
      <p>浙ICP备2025145364号</p>
    </div>
    </div>
  </template>

  <script>
  export default {
    name: 'Home',
    data() {
      return {
        // 如果需要动态数据可以在这里定义
      }
    }
  }
  </script>

  <style scoped>
  .home {
    width: 100%;
    min-height: 100vh;
  }

  .header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 20px 50px;
    background: white;
    box-shadow: 0 2px 4px rgba(0,0,0,0.1);
  }

  .logo {
    display: flex;
    align-items: center;
  }

  .logo img {
    height: 60px;
    margin-right: 20px;
  }

  .company-name h1 {
    font-size: 24px;
    color: #333;
    margin: 0;
  }

  .company-name h2 {
    font-size: 16px;
    color: #666;
    margin: 5px 0 0 0;
  }

  .nav-menu ul {
    display: flex;
    list-style: none;
    margin: 0;
    padding: 0;
  }

  .nav-menu li {
    margin-left: 30px;
    cursor: pointer;
    font-size: 16px;
    color: #333;
  }

  .banner {
    position: relative;
    width: 100%;
    height: 600px;
  }

  .banner img {
    width: 100%;
    height: 100%;
    object-fit: cover;
  }

  .banner-text {
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    text-align: center;
    color: white;
    text-shadow: 0 2px 4px rgba(0,0,0,0.5);
  }

  .banner-text h3 {
    font-size: 32px;
    margin-bottom: 20px;
  }

  .banner-text p {
    font-size: 18px;
    line-height: 1.6;
  }

  .company-profile {
    padding: 60px 100px;
    text-align: center;
    background: #f5f5f5;
  }

  .company-profile h2 {
    font-size: 36px;
    color: #333;
    margin-bottom: 10px;
  }

  .company-profile h3 {
    font-size: 24px;
    color: #666;
    margin-bottom: 40px;
  }

  .profile-content {
    text-align: left;
    max-width: 1200px;
    margin: 0 auto;
  }

  .profile-content p {
    font-size: 16px;
    line-height: 1.8;
    color: #444;
  }

  .training-center {
    text-align: center;
    padding: 40px 0;
  }

  .training-center img {
    width: 100%;
    max-width: 1200px;
    margin-bottom: 20px;
  }

  .drone-display {
    padding: 60px 100px;
    background: #f5f5f5;
  }

  .drone-display h2 {
    text-align: center;
    font-size: 36px;
    color: #333;
    margin-bottom: 10px;
  }

  .drone-display h3 {
    text-align: center;
    font-size: 24px;
    color: #666;
    margin-bottom: 40px;
  }

  .display-items {
    display: flex;
    justify-content: space-between;
    max-width: 1200px;
    margin: 0 auto;
  }

  .display-item {
    width: 30%;
    text-align: center;
  }

  .display-item img {
    width: 100%;
    border-radius: 8px;
    margin-bottom: 15px;
  }
  .contact-info {
  display: flex;
  justify-content: space-around;
  max-width: 1000px;
  margin: 0 auto;
  padding: 40px 0;
}

/* 页脚样式 */
.footer {
  background: #333;
  color: white;
  text-align: center;
  padding: 20px 0;
}

  .display-item p {
    font-size: 18px;
    color: #333;
  }
  </style>
