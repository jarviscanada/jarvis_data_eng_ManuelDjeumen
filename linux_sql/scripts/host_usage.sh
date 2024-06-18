
psql_host=$1
psql_port=$2
db_name=$3
psql_user=$4
psql_password=$5

if [ "$#" -ne 5 ]; then
	echo "The number of parameters is wrong"
	exit 1
fi

vmstat_mb=$(vmstat --unit M)
hostname=$(hostname -f)


memory_free=$(echo "$vmstat_mb" | tail -1 | awk -v col="4" '{print $col}')
cpu_idle=$(echo "$vmstat_mb" | tail -1 | awk -v col="15" '{print $col}')
cpu_kernel=$(echo "$vmstat_mb" | tail -1 | awk -v col="14" '{print $col}')
disk_io=$(echo "$vmstat_mb" -d | tail -1 | awk -v col="10" '{print $col}')
disk_available=$(df -BM / | tail -1 | awk -v col="4" '{print $col}' | cut -d'M' -f1)


timestamp=$(vmstat -t | awk '{print $18,$19}'| tail -n1 | xargs)

#host_id=$(vmstat --unit M | awk '{print $15}' | tail -n1 | xargs)


host_id="(SELECT id FROM host_info WHERE hostname='$hostname')";

insert_stmt="INSERT INTO host_usage("timestamp", host_id, memory_free, cpu_idle, cpu_kernel, disk_io, disk_available) VALUES('$timestamp', '$host_id', '$memory_free', '$cpu_idle', '$cpu_kernel', '$disk_io', '$disk_available')"



#set up env var for pql cmd
export PGPASSWORD=$psql_password


#Insert date into a database
psql -h $psql_host -p $psql_port -d $db_name -U $psql_user -c "$insert_stmt"
exit $?
