脚本使用说明  
---------------------------  
  
# 前提条件  
1.	安装Java环境  
2.	安装JMeter（脚本使用JMeter3.0撰写）  
3.	将数据文件(keys.txt, resp.txt)放到%JMETER_HOME%\BIN目录下；或者修改脚本配置，指向数据文件。  
  
# 使用说明  
insitesearch.jmx – jmeter测试项目文件，使用jmeter打开。  
  
random_key.py脚本可随机生成搜索关键字的文件keys.txt，需python环境运行。  
  
在测试计划节点中，定义了2个关键参数  
vuser_number – 多少个并发用户  
request_per_vuser – 每个用户执行多少次搜索请求  
  
1. 搜索-并发请求  
模拟${ vuser_number}个并发用户同时发起搜索请求（类似LR的集合点），然后进入一轮请求发起；下一轮也是${ vuser_number}个用户同时集合完毕后，开始请求。循环${ request_per_vuser}次。    

2. 搜索-模拟多用户  
模拟${ vuser_number}个用户发起请求，最开始所有用户集合后开始发起搜索请求。此后，每个用户每次发送请求后，休息随机的(300+/-100毫秒)时间，再进入一轮请求发起。循环${ request_per_vuser}次。  
  
# 附录： 文件说明  
insitesearch.jmx - jmeter测试项目文件，使用jmeter打开  
  
keys.txt - 搜索关键字数据文件  
random_key.py - 随机生成搜索关键字数据文件的python脚本  
resp.txt - 响应预期数据文件  
  
perftest.doc - 本demo的说明文档  
data.statistics.list.xls - 测试数据汇总整理  
results-10 - 10并发用户的测试数据  
results-100 - 100并发用户的测试数据  
results-200 - 200并发用户的测试数据  
results-25 - 25并发用户的测试数据  
results-400 - 400并发用户的测试数据  
results-50 - 50并发用户的测试数据  
  