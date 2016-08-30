#!/usr/bin/env bash

#echo "usage" > &2
curday=`date "+%Y%m%d-%T"`
echo $curday
export filename=jvm-track-$1-$curday.log
echo $filename
#exit 0

if [ $# -lt 1 ] ;then
    echo "Usage: $0 java_pid " 
    exit 1
fi

#check wether a java pid
isjava=`ps --no-heading $1|grep java|wc -l`
if [ $isjava -lt 1 ] ;then
    echo $1 is not a java process
    exit 1
fi

#################################################### 
curday=`date "+%Y%m%d-%T"`
filename=jvm-track-$1-$curday.log
pid=$1
echo filename : $filename
echo clean file $filename

date |tee $filename

# call java command with 1 option param
# usage : callJC2P java_command command_option
# example : callJC2P jstat gc gccapacity 
function callJC2P
{
  excutor=$1
  shift  
  echo '#######################################'|tee -a $filename
  echo $excutor |tee -a $filename
  echo ''|tee -a $filename
  while [ $# -gt 0 ]; do 
    echo  |tee -a $filename
    echo $excutor -$1 $pid |tee -a $filename
    $excutor -$1 $pid  |tee -a $filename 
    shift
  done
}

# jinfo
echo jinfo |tee -a $filename
echo  |tee -a $filename
jinfo $pid |tee -a $filename

 
# jstat
callJC2P jstat gc gccapacity gccause gcnew gcnewcapacity gcold gcoldcapacity gcpermcapacity gcutil

# jmap
callJC2P jmap heap histo dump:file=$pid.dump

# jstack
callJC2P jstack l F 
jstack $pid|tee -a $filename
#permstat

