#FROM buildpack-deps:sid-scm

FROM ubuntu:16.04

MAINTAINER Thembela Kwenene (thembela@kineticskunk.com)

# this is a non-interactive automated build - avoid some warning messages
ENV DEBIAN_FRONTEND noninteractive

# update dpkg repositories
RUN apt-get update 

# install wget
RUN apt-get install -y wget &&\
    # get maven 3.3.9
    wget --no-verbose -O /tmp/apache-maven-3.3.9.tar.gz http://archive.apache.org/dist/maven/maven-3/3.3.9/binaries/apache-maven-3.3.9-bin.tar.gz &&\

    # verify checksum
    echo "516923b3955b6035ba6b0a5b031fbd8b /tmp/apache-maven-3.3.9.tar.gz" | md5sum -c &&\

  # install maven
  tar xzf /tmp/apache-maven-3.3.9.tar.gz -C /opt/ &&\
  ln -s /opt/apache-maven-3.3.9 /opt/maven &&\
  ln -s /opt/maven/bin/mvn /usr/local/bin &&\
  rm -f /tmp/apache-maven-3.3.9.tar.gz &&\
  MAVEN_HOME /opt/maven

# remove download archive files
RUN apt-get clean
# install java 
RUN apt-get update && \
    DEBIAN_FRONTEND=noninteractive \
    apt-get -y install default-jre-headless && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*
CMD [""]


