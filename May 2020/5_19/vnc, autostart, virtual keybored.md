# vnc, autostart, virtual keybored - 완료

- 설치 패키지 확인
  - 레드햇
    - rpm -qa
  - 데비안
    - dpkg --get-selections

***

- 심볼링 링크 생성, 삭제 - https://fruitdev.tistory.com/85
  - 생성
    - ln -s [원본파일 or dir] [생성할 심볼링링크 이름]
  - 삭제
    - rm [심볼릭링크 이름]

***

### 스크립트 자동실행 - systemctl 

https://www.linode.com/docs/quick-answers/linux/start-service-at-boot/

- [systemd 사운드 관련 이슈](https://stackoverflow.com/questions/49059610/pygame-error-no-available-audio-device-when-running-pygame-inside-systemd-wit)

  ```
  # sudo vi /etc/systemd/system/multi-user.target.wants/charger_on.service 
  
  [Unit]
  Description="Start Touch Charger Script on Boot"
  After=network.target
  
  [Service]
  Type=simple
  #ExecStart=/usr/bin/python3 /home/charger/Public/main.py
  ExecStart=/bin/bash /home/charger/charger_on.sh
  Environment=DISPLAY=:0
  Environment=HOME=/home/charger
  WorkingDirectory=/home/charger/Public
  StandardOutput=inherit
  StandardError=inherit
  User=charger
  Restart=on-failure
  RestartSec=5
  
  
  [Install]
  WantedBy=multi-user.target
  
  ```

  

## 시스템이란 무엇입니까?[퍼머 링크](https://www.linode.com/docs/quick-answers/linux/start-service-at-boot/#what-is-systemd)

systemd는 Red Hat Linux 팀이 처음 개발 한 Linux 시스템 도구입니다. 시스템 프로세스를 시작하고 관리하는 데 사용되는 부트 스트랩 시스템을 포함하여 많은 기능이 포함되어 있습니다. 현재 대부분의 Linux 배포에서 기본 초기화 시스템입니다. SSH 및 Apache와 같이 일반적으로 사용되는 많은 소프트웨어 도구는 시스템 서비스와 함께 제공됩니다.

선택한 스크립트 나 프로세스를 실행할 맞춤형 시스템 서비스를 작성하는 것은 간단합니다. Linode가 부팅 될 때 스크립트를 실행하거나 프로세스를 시작하는 방법에는 여러 가지가 있지만 사용자 정의 시스템 서비스를 사용하면 스크립트를 쉽게 시작, 중지 또는 다시 시작하고 부팅시 자동으로 시작되도록 구성 할 수 있습니다. systemd는이 [를 지원하는](https://en.wikipedia.org/wiki/Systemd#Adoption) 모든 [Linux 배포판에서](https://en.wikipedia.org/wiki/Systemd#Adoption) 일관된 표준화 된 인터페이스를 사용하는 이점 [을 제공합니다](https://en.wikipedia.org/wiki/Systemd#Adoption) .

## 맞춤형 시스템 서비스 생성[퍼머 링크](https://www.linode.com/docs/quick-answers/linux/start-service-at-boot/#create-a-custom-systemd-service)

1. 서비스가 관리 할 스크립트 또는 실행 파일을 작성하십시오. 이 안내서는 간단한 Bash 스크립트를 예로 사용합니다.

   - test_service.sh

     `1 2 3 4 5 6 7 8 ``DATE=`date '+%Y-%m-%d %H:%M:%S'` echo "Example service started at ${DATE}" | systemd-cat -p info while : do echo "Looping..."; sleep 30; done`

   이 스크립트는 스크립트가 초기화 된 시간을 기록한 다음 서비스가 계속 실행되도록 무한 반복됩니다.

2. 스크립트를 복사하여 `/usr/bin`실행 가능하게하십시오.

   

   ```
   sudo cp test_service.sh /usr/bin/test_service.sh
   sudo chmod +x /usr/bin/test_service.sh
   ```

3. 시스템 서비스를 정의하기 위해 **Unit 파일** 을 작성하십시오 .

   - /lib/systemd/system/myservice.service

     `1 2 3 4 5 6 7 8 9 ``[Unit] Description=Example systemd service. [Service] Type=simple ExecStart=/bin/bash /usr/bin/test_service.sh [Install] WantedBy=multi-user.target`

   이것은 간단한 서비스를 정의합니다. 중요한 부분은 `ExecStart`서비스를 시작하기 위해 실행될 명령을 지정 하는 지시문입니다.

4. 유닛 파일을 복사하여 `/etc/systemd/system`권한을 부여하십시오.

   

   ```
   sudo cp myservice.service /etc/systemd/system/myservice.service
   sudo chmod 644 /etc/systemd/system/myservice.service
   ```

   장치 파일 및 사용 가능한 구성 옵션에 대한 자세한 내용은 시스템 [설명서를](https://www.freedesktop.org/wiki/Software/systemd/) 참조하십시오 .

데비안 제시 이후, 이전 `init`프로그램은 **`systemd`**!

직접 확인하십시오. 실행 : `ls -l /sbin/init`그리고 그것이 무엇을 가리키는 지 봅니다. 요즘에는 새롭고 더 나은 초기화 프로그램 인 `systemd`( `/sbin/init -> /lib/systemd/systemd`)을 가리 킵니다 .

------

**그래서 지금 선택된 CHOSEN과 다른 답변들이 있습니다!**

------

\* 여전히 System-V 도구를 사용하여 스크립트를 설치할 수는 있지만 일반적으로 말하는 것은 좋지 않습니다.

`man systemd.service` 말한다 : *

> *특정 이름으로 서비스가 요청되었지만 장치 구성 파일이없는 경우 systemd는 동일한 이름으로 SysV 초기화 스크립트를 찾고 해당 스크립트에서 서비스 장치를 동적으로 생성합니다. SysV와의 호환성에 유용합니다. 이 호환성은 매우 포괄적이지만 100 %는 아닙니다.*

------

## 최신 데비안 시스템 (예 : Jessie, Stretch, Buster 등)

생각보다 쉽습니다. (-:

다음은 부팅 또는 종료 프로그램을 설치하는 새롭고 선호되는 방법입니다.

함께 `systemd`하면 먼저 만들려는 **장치 파일을** . 단위 파일은 대부분 코드가 아닌 선언입니다.

그런 다음 **`systemctl`**명령을 사용하여 해당 장치 를 **활성화** 하거나 **시작** 합니다.

`systemd`예를 들어 중요한 프로그램이 충돌하거나 다른 방식으로 종료되면 자동으로 다시 시작하도록하는 등 많은 작업을 수행합니다. 또한 기본적으로 추가 작업없이 프로그램을 언제 어디서나 종료합니다.

------

**`systemd`여기 에 대해 배우기 시작 하십시오.**

[데비안에서](https://debian-handbook.info/browse/stable/unix-services.html#sect.system-boot) 와 [다른 곳](https://www.linux.com/learn/understanding-and-using-systemd) 과 [등](https://www.digitalocean.com/community/tutorials/understanding-systemd-units-and-unit-files) 및 [등](https://fedoramagazine.org/systemd-getting-a-grip-on-units/) .

------

**맨 페이지 시작 :**

`man systemd.unit` -일반적으로 장치 파일 정보
`man systemd.service` -서비스 장치 파일 (예 : 데몬 및 단일 실행 프로그램) 정보

`man  systemctl` -명령 행 사용자 인터페이스
`man journalctl`-systemd가 수행 한 작업의 로그보기

`man systemd` -init 프로그램 자체에 대하여

------

다양한 다른 유형의 단위 파일도 있습니다. 예 :

`man systemd.target` -그룹 및 공통 동기화 대상의 경우.

위의 기본 사항을 요약 한 후 `man -k systemd`다른 관련 매뉴얼 페이지를 찾아보십시오.

------

------

무엇을 하든지 데비안 **에서는 다음 중 하나를 사용하지 마십시오** .

- `update-rc.d` --System-V 스타일 init 스크립트 링크 설치 및 제거
- `sysv-rc-conf` -init 스크립트 링크와 같은 SysV의 실행 수준 구성
- `runlevel` -이전 및 현재 SysV 런레벨 인쇄
- `BUM`- **B** OOT **U** P는 **M의** anager - 그래픽 런레벨 편집기
- `systemadm`-시스템 시스템 및 서비스 관리자를위한 그래픽 프론트 엔드
  (BTW, 저자는 이메일이 너무 고장 났다고 나에게 말했다.)



***

### x11vnc 

- 참고 자료 
  - [x11vnc 설치](https://linuxconfig.org/how-to-share-your-desktop-in-linux-using-x11vnc)
  - [데비안 9에서 x11vnc 설치 하는 영상 ](https://www.youtube.com/watch?v=US7gaqDgAxM&t=1327s)
  - [x11vnc 비밀번호 설정 ](https://help.ubuntu.com/community/VNC/Servers#Have_x11vnc_start_automatically_via_systemd_in_any_environment_.28Vivid.2B-.29)
- [x11vnc 이슈](https://forum.ubuntuusers.de/topic/keine-verbindung-mehr-zu-x11vnc-vnc/)
  

```shell
$ sudo vi /etc/systemd/system/multi-user.target.wants/x11vnc.service 


[Unit]
Description="Start X11 VNC Server Open"
After=network.target

[Service]
Type=simple
ExecStart=/usr/bin/x11vnc -display :0 -auth guess -forever -loop -noxdamage -repeat -rfbauth /etc/x11vnc.passwd -rfbport 5900 -shared
ExecStop=/usr/bin/killall x11vnc
Environment=DISPLAY=:0
User=charger
Restart=on-failure
Restart-sec=5

[Install]
WantedBy=multi-user.target

```

***

###  프로그램 내 가상키보드 실행

```shell
florence 사용

$ sudo apt-get install florence 
```

