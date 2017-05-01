# logAggregator
aggregates url logs to track user trends

How to use 
----------
* Enter the directory for the logs :/data/logs
* see example data in data/logs/

Here’s one example line:

`10.10.6.90 - - 15/Aug/2016:23:59:20 -0500 "GET /ecf8427e/b443dc7f/71f28176/174ef735/1dd4d421 HTTP/1.0" 200 - "-" "-" 7 "10.10.1.231, 10.10.6.90" -`

* User ID here is the 3rd URI path element is a user id, e.g., 71f28176 above
* Assume that a user session is defined as a series of page accesses during which all *consecutive* page accesses are <= 10 minutes apart. So e.g. if a given user accesses pages at time t1=13:00, t2=13:05, t3=13:14, t4=14:11, and t5=14:19, then they are defined to have had 2 sessions, the first with requests at times t1-t3 and the second with requests at times t4-t5, per the following table:

| tprev | tcurr | delta | session # |
|-------| ----- |-------| ---------|
|N/A  |   13:00  |    N/A   |    1|
|13:00 |  13:05 |      5 min |    1|
|13:05 |  13:14 |      9 min  |    1|
|13:14  | 14:11  |     57 min |     2|
|14:11  | 14:19 |    8 min     |    2|

* After aggregating all data from all log files, a report is generated to the standard output with the following information:
* Total number of unique users who visited
* For each data set top 5 unique users for total number page views (not unique views), in descending order of page views:
* User id 
* Total number of page views per user
* Total number of sessions per user
* Length in minutes of the longest session per user
* Length in minutes of the shortest session per user

Here’s an example report with only 3 instead of 5 users:

Total unique users: 27
Top users:
id              # pages # sess  longest shortest
71f28176        75      3       35      1
41f58122        65      4       60      10

58122233        44      2       121     3


![alt text](https://github.com/dipika-m/logAggregator/blob/master/result/Screen%20Shot%202017-05-01%20at%204.37.01%20AM.png "Sample")
![alt text](https://github.com/dipika-m/logAggregator/blob/master/result/Screen%20Shot%202017-05-01%20at%204.38.27%20AM.png "Sample2")
