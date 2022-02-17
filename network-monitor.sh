#!/bin/bash
rm -f output.csv
rm -f output.json
speedtest --csv>output.txt
cat output.txt>>output.csv

IFS= read -r line < output.csv
echo "$line"
array=""
IFS=',' read -r -a array <<< "$line"

for element in "${array[@]}"
do
    echo "$element"
done
echo "INSERT INTO network_statistics (timestamp, ping, download, upload, server_id, server_name, ip_address) VALUES (to_timestamp('${array[3]}', 'YYYY-MM-DDXHH24:MI:SS.XXXXXXX'),${array[5]},${array[6]},${array[7]},'${array[0]}','${array[1]}','${array[9]}');"
psql -b -e [your postgresql connection] <<EOF
INSERT INTO network_statistics (timestamp, ping, download, upload, server_id, server_name, ip_address) VALUES (to_timestamp('${array[3]}', 'YYYY-MM-DDXHH24:MI:SS.XXXXXXX'),${array[5]},${array[6]},${array[7]},'${array[0]}','${array[1]}','${array[9]}');
EOF