## 前言
非常感谢您愿意协助EasyExcel的开发，EasyExcel成长离不开大家的贡献。但是为了合作的更有效率，希望我们在贡献代码的时候能按照如下约定。
## 提前沟通
尽量把自己的想法和实现思路提前沟通，可以通过issue,钉钉,QQ都可以，可能很多问题我们内部已经讨论过，由于各种原因后续不会支持，但是您这边又开发好了，这样容易浪费您的时间。
## 代码规范
请先安装阿里巴巴代码规约插件！！！https://plugins.jetbrains.com/plugin/10046-alibaba-java-coding-guidelines    
目前代码规范已经集成了自动校验，然后源代码尽量不要有中文注释。在新增功能的时候，尽量注意补充junit。core代表每次travis-ci都会跑的测试案例，然后demo用于对外看到，temp里面随便写。
## 提交分支
建议提交到最新的版本号.x上面，比如 3.x之类的版本，为了方便其他同学阅读源代码，所以目前的思路是master和maven center的最新版本代码保持一致，然后您提交过来的代码我们可能会稍微做一些修改。所以提交到开发分支会比较好。fork也可以直接fork该分支。