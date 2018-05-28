FROM centos:7
MAINTAINER Thembela Kwenene <thembela@kineticskunk.com>

# Install Essentials
RUN yum update -y && \
    yum clean all

ENV JAVA_VERSON 1.8.0
ENV MAVEN_VERSION 3.3.9

# Install Packages
RUN yum install -y git && \
    yum install -y curl && \
	yum install -y java-$JAVA_VERSON-openjdk java-$JAVA_VERSON-openjdk-devel && \
	yum install -y sudo && \
	yum clean all

# get maven 3.3.9
RUN curl -fsSL https://archive.apache.org/dist/maven/maven-3/$MAVEN_VERSION/binaries/apache-maven-$MAVEN_VERSION-bin.tar.gz | tar xzf - -C /usr/share \
  && mv /usr/share/apache-maven-$MAVEN_VERSION /usr/share/maven \
  && ln -s /usr/share/maven/bin/mvn /usr/bin/mvn \
  && yum clean all



# gen dummy keys, centos doesn't autogen them like ubuntu does
RUN /usr/bin/ssh-keygen -A

# Set SSH Configuration to allow remote logins without /proc write access
#RUN sed -ri 's/^session\s+required\s+pam_loginuid.so$/session optional pam_loginuid.so/' /etc/pam.d/sshd

# Create Jenkins User
RUN useradd jenkins -m -s /bin/bash

# Add public key for Jenkins login
RUN mkdir /home/jenkins/.ssh

COPY /files/authorized_keys /home/jenkins/.ssh/authorized_keys

RUN chown -R jenkins /home/jenkins
RUN chgrp -R jenkins /home/jenkins
RUN chmod 600 /home/jenkins/.ssh/authorized_keys
RUN chmod 700 /home/jenkins/.ssh

# Add the jenkins user to sudoers
RUN echo "jenkins    ALL=(ALL)    ALL" >> etc/sudoers

# Set Name Servers
#COPY /files/resolv.conf /etc/resolv.conf

# Expose SSH port and run SSHD
EXPOSE 22

ENV JAVA_HOME /usr/lib/jvm/java
ENV MAVEN_HOME /usr/share/maven

CMD ["java", "-jar", "/usr/lib/jenkins/jenkins.war"]
#CMD ["/usr/sbin/sshd","-D"]
