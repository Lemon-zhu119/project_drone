<template>
  <div class="terms-of-service">
    <h1>服务协议（Terms of Service）</h1>
    <p>欢迎使用“壹号翼”（以下简称"应用"）。</p>
    <p>本服务协议（以下简称"协议"）由“温州壹号翼科技有限公司”（以下简称"我们"或"服务提供者"）与用户（以下简称"用户"）之间签订，适用于用户使用我们的应用。</p>
    <p>通过下载、安装或使用本应用，即表示用户同意遵守本协议的所有条款和条件。如果用户不同意本协议的任何条款，请勿下载、安装或使用本应用。</p>

    <h2>第1条 服务内容</h2>
    <p>1.1 我们提供无人机考证的题库服务。</p>
    <p>1.2 用户可以通过本应用进行题库的练习。</p>

    <h2>第2条 用户账户</h2>
    <p>2.1 用户需自行注册账户以使用本应用的某些功能。</p>
    <p>2.2 用户应自行保管账户信息，并对账户下的所有活动负责。</p>
    <p>2.3 用户不得将账户信息转让、出借给他人使用。</p>

    <h2>第3条 用户责任</h2>
    <p>3.1 用户应合法使用本应用，不得利用本应用从事违法活动。</p>
    <p>3.2 用户不得干扰本应用的正常运行，不得侵入本应用的服务器。</p>
    <p>3.3 用户不得发布、传播任何违法、侵权、侮辱性或不适当的内容。</p>

    <h2>第4条 知识产权</h2>
    <p>4.1 本应用及其所含内容（包括但不限于软件、文字、图片、音频、视频等）的知识产权归我们所有。</p>
    <p>4.2 用户不得擅自复制、修改、传播本应用的任何内容。</p>
    <p>4.3 用户在使用本应用过程中产生的任何内容，我们有权进行使用、复制、发布等。</p>

    <h2>第5条 隐私政策</h2>
    <p>5.1 我们的隐私政策详见 [附上隐私政策链接或文件]。</p>
    <p>5.2 用户在使用本应用时，我们可能会收集和使用用户的某些个人信息，具体详见我们的隐私政策。</p>

    <h2>第6条 免责声明</h2>
    <p>6.1 我们尽力提供高质量的服务，但不保证本应用无缺陷或不会中断。</p>
    <p>6.2 对于因使用本应用或无法使用本应用导致的任何损失，我们不承担责任。</p>
    <p>6.3 本应用可能包含第三方链接，我们对这些第三方网站的内容和隐私政策不承担责任。</p>

    <h2>第7条 协议的修改</h2>
    <p>7.1 我们保留随时修改本协议的权利。</p>
    <p>7.2 修改后的协议将在本应用内公布，用户继续使用本应用即表示同意新的协议。</p>

    <h2>第8条 协议的终止</h2>
    <p>8.1 用户可以随时停止使用本应用，终止本协议。</p>
    <p>8.2 如果用户违反本协议的任何条款，我们有权终止用户的账户和使用权。</p>
    <p>8.3 协议终止后，用户使用本应用的权利将立即终止。</p>

    <h2>第9条 法律适用与争议解决</h2>
    <p>9.1 本协议的签订、效力、解释及争议的解决均适用 [你所在地的法律]。</p>
    <p>9.2 因本协议引起的任何争议，双方应首先通过友好协商解决；协商不成的，任何一方均可向 [你所在地的法院] 提起诉讼。</p>

    <p>温州壹号翼科技有限公司</p>
    <p>2025年3月1日</p>
  </div>
</template>

<script>
export default {
  name: 'TermsOfService'
}
</script>

<style scoped>
.terms-of-service {
  padding: 40px 20px;
  max-width: 1200px;
  margin: 0 auto;
  line-height: 1.8;
  color: #333;

  .container {
    background: #fff;
    padding: 40px;
    border-radius: 8px;
    box-shadow: 0 2px 12px rgba(0, 0, 0, 0.1);

    h1 {
      text-align: center;
      color: #2c3e50;
      margin-bottom: 20px;
      font-size: 2em;

      h2 {
        color: #2c3e50;
        margin: 30px 0 20px;
        padding-bottom: 10px;
        border-bottom: 2px solid #eee;
        font-size: 1.5em;

        h3 {
          color: #2c3e50;
          margin: 25px 0 15px;
          font-size: 1.2em;

          h4 {
            color: #2c3e50;
            margin: 20px 0 10px;
            font-size: 1.1em;

            .last-updated {
              text-align: center;
              color: #666;
              margin-bottom: 40px;
              font-style: italic;

              section {
                margin-bottom: 40px;

                p {
                  margin-bottom: 15px;
                  line-height: 1.8;

                  ul {
                    padding-left: 20px;
                    margin-bottom: 20px;

                    li {
                      margin-bottom: 15px;
                      line-height: 1.6;

                      strong {
                        color: #2c3e50;

                        a {
                          color: #409EFF;
                          text-decoration: none;

                          a:hover {
                            text-decoration: underline;

                            .generated-by {
                              text-align: center;
                              color: #666;
                              font-style: italic;
                              margin-top: 40px;
                              padding-top: 20px;
                              border-top: 1px solid #eee;

                              @media (max-width: 768px) {
                                .terms-of-service {
                                  padding: 20px 10px;
                                }

                                .container {
                                  padding: 20px;
                                }

                                h1 {
                                  font-size: 1.8em;
                                }

                                h2 {
                                  font-size: 1.3em;
                                }

                                h3 {
                                  font-size: 1.1em;
                                }

                                h4 {
                                  font-size: 1em;
                                }
                              } 
                            }
                          }
                        }
                      }
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
  }
}
</style>