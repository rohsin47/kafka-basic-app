# Benchmark commands

## Producer

Setup

```sh
bin/kafka-topics.sh \
  --zookeeper localhost:2181 \
  --create \
  --topic rep_perf_test \
  --partitions 3 --replication-factor 3
```

# Producer Throughput: Three producer thread, no replication, no compression

```sh
bin/kafka-producer-perf-test.sh --topic rep_perf_test \
--num-records 15000000 \
--record-size 100 \
--throughput 15000000 \
--producer-props \
acks=1 \
bootstrap.servers=localhost:9092,localhost:9093,localhost:9094 \
buffer.memory=67108864 \
compression.type=none \
batch.size=8196
```

# Producer Throughput: Three producer thread, no replication, zstandard compression

```sh
bin/kafka-producer-perf-test.sh --topic rep_perf_test \
--num-records 15000000 \
--record-size 100 \
--throughput 15000000 \
--producer-props \
acks=1 \
bootstrap.servers=localhost:9092,localhost:9093,localhost:9094 \
buffer.memory=67108864 \
compression.type=zstd \
batch.size=8196
```

## Producer Throughput: Three producer thread, 3x asynchronous replication, no compression

```sh
bin/kafka-producer-perf-test.sh --topic rep_perf_test \
--num-records 15000000 \
--record-size 100 \
--throughput 15000000 \
--producer-props \
acks=1 \
bootstrap.servers=localhost:9092,localhost:9093,localhost:9094 \
buffer.memory=67108864 \
compression.type=none \
batch.size=8196
```

## Producer Throughput: Three producer thread, 3x synchronous replication, no compression
## ack=-1 is equivalent to all, this is the strongest gurantee where leader will wait for the 
## full set of in-sync replicas to acknowledge the record

```sh
bin/kafka-producer-perf-test.sh --topic rep_perf_test \
--num-records 15000000 \
--record-size 100 \
--throughput 15000000 \
--producer-props \
acks=-1 \
bootstrap.servers=localhost:9092,localhost:9093,localhost:9094 \
buffer.memory=67108864 \
compression.type=none \
batch.size=8196
```

## Throughput Versus Stored Data

```sh
bin/kafka-producer-perf-test.sh \
  --topic rep_perf_test \
  --num-records 50000000 \
  --record-size 100 \
  --throughput -1 \
  --producer-props acks=1 \
  bootstrap.servers=localhost:9092,localhost:9093,localhost:9094 \
  buffer.memory=67108864 
  batch.size=8196
```

## Effect of message size

```sh
for i in 10 100 1000 10000 100000; do
  echo ""
  echo $i
  bin/kafka-producer-perf-test.sh \
    --topic test \
    --num-records $((1000*1024*1024/$i))\
    --record-size $i\
    --throughput -1 \
    --producer-props acks=1 \
    bootstrap.servers=kafka.example.com:9092 \
    buffer.memory=67108864 \
    batch.size=128000
done;
```

## Consumer

Consumer throughput

```sh
bin/kafka-consumer-perf-test.sh \
  --zookeeper zookeeper.example.com:2181 \
  --messages 50000000 \
  --topic test \
  --threads 1
```

3 Consumers

On three servers, run:

```sh
bin/kafka-consumer-perf-test.sh \
  --zookeeper zookeeper.example.com:2181 \
  --messages 50000000 \
  --topic test \
  --threads 1
```

## End-to-end Latency

```
bin/kafka-run-class.sh \
  kafka.tools.TestEndToEndLatency \
  kafka.example.com:9092 \
  zookeeper.example.com:2181 \
  test 5000
```
