FROM ubuntu:latest
LABEL authors="olive"

ENTRYPOINT ["top", "-b"]