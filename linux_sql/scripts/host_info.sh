

psql_host=$1
psql_port=$2
db_name=$3
psql_user=$4
psql_password=$5

if [ "$#" -ne 5 ]; then
    echo "The number of parameters is wrong!"
    exit 1
fi

hostname=$(hostname -f)
lscpu_out=`lscpu`
vmstat_mb=$(vmstat --unit M)



host_id=$(vmstat --unit M | awk '{print $15}' | tail -n1 | xargs)
cpu_number=$(echo "$lscpu_out"  | egrep "^CPU\(s\):" | awk '{print $2}' | xargs)
cpu_architecture=$(echo "$lscpu_out"  | egrep "^Architecture:" | awk '{print $2}' | xargs)
cpu_model=$(echo "$lscpu_out"  | egrep "^Model name:" | awk '{print $3 $4}' | xargs)
cpu_mhz=$(echo "$lscpu_out"  | egrep "^Model name:" | awk '{print $7}' | xargs | cut -d'G' -f1)
l2_cache=$(echo "$lscpu_out"  | egrep "^L2 cache:" | awk '{print $3}' | xargs)
total_mem=$(echo "$vmstat_mb" | tail -n1 | awk '{print $4}'| xargs)
timestamp=$(vmstat -t | awk '{print $18, $19}'| tail -n1 | xargs)


# Subquery to find matching id in host_info table
#host_id=$(vmstat --unit M | awk '{print  $15}'| tail -n1 | xargs)

insert_stmt="INSERT INTO host_info(id, hostname, cpu_number, cpu_architecture, cpu_model, cpu_mhz, l2_cache, "timestamp", total_mem) VALUES('$host_id', '$hostname', '$cpu_number', '$cpu_architecture', '$cpu_model', '$cpu_mhz', '$l2_cache', '$timestamp', '$total_mem')";  

#set up env var for pql cmd
export PGPASSWORD=$psql_password 



#Insert date into a database
psql -h $psql_host -p $psql_port -d $db_name -U $psql_user -c "$insert_stmt"
exit $?
