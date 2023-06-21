FROM debian:11.5-slim

# Install Chromium and Firefox
RUN apt-get update -y
RUN apt-get upgrade -y
RUN apt-get install -y \
        firefox-esr \
        zip \
        wget \
        openjdk-17-jdk \
        git \
        binutils \
        fakeroot

# Install Android SDK
RUN mkdir -p /usr/local/android-sdk/cmdline-tools/latest
RUN wget -O /tmp/sdk.zip https://dl.google.com/android/repository/commandlinetools-linux-9123335_latest.zip \
    && unzip /tmp/sdk.zip -d /tmp/ \
    && mv /tmp/cmdline-tools/* /usr/local/android-sdk/cmdline-tools/latest \
    && rm -f /tmp/sdk.zip \
    && rm -rf /tmp/cmdline-tools
ENV ANDROID_HOME /usr/local/android-sdk
ENV PATH $ANDROID_HOME/cmdline-tools/latest/bin:$ANDROID_HOME/platform-tools:$PATH
RUN sdkmanager "platform-tools" "platforms;android-33"
RUN yes | sdkmanager --sdk_root=$ANDROID_HOME --licenses