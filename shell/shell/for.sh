# !/bin/sh

for ((i=1; i<=10000 ; i++)); do
        curl http://127.0.0.1/seqgenerator/seq/topic
        curl http://127.0.0.1/seqgenerator/seq/reply
        curl http://127.0.0.1/seqgenerator/seq/praise
        curl http://127.0.0.1/seqgenerator/seq/share
        echo loop $i done
done
