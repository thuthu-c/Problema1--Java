#!/usr/bin/env bash

cd src
javac -d ../build br/ufrn/summarizer/*.java
cd ../build
java br.ufrn.summarizer.ParallelArraySummarizer $1 $2
