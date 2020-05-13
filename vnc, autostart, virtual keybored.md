# vnc, autostart, virtual keybored

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

### 스크립트 자동실행 

- 시도 - program boot autostart in debian 10 , Automatically run at boot in debian 10검색
  - https://geekflare.com/how-to-auto-start-services-on-boot-in-linux/
  - https://www.tecmint.com/auto-execute-linux-scripts-during-reboot-or-startup/
  - https://serverfault.com/questions/10811/how-to-add-a-script-to-startup-and-shutdown-in-debian





***

real vnc 

- Raspberry Pi와 같은 일부 프로젝트에는 Pis가 RealVnc 서버를 실행할 수있는 특수 라이센스가 부여되었습니다. 나머지 세계는 지불해야합니다.

***

### x11vnc - 자동실행 X

- 참고 자료 
  - [x11vnc 설치](https://linuxconfig.org/how-to-share-your-desktop-in-linux-using-x11vnc)
  - [데비안 9에서 x11vnc 설치 하는 영상 ](https://www.youtube.com/watch?v=US7gaqDgAxM&t=1327s)
  - [x11vnc 비밀번호 설정 ](https://help.ubuntu.com/community/VNC/Servers#Have_x11vnc_start_automatically_via_systemd_in_any_environment_.28Vivid.2B-.29)

- x11vnc 설치 , 방화벽 5900 포트 열어둠 
- /lib/systemd/system 에서 x11vnc.service 파일 만듬 

```vi 첫번째 수정
# x11vnc.service

[Unit]
Description=Start x11vnc at startup.
After=multi-user.target

[Service]
Type=simple
ExecStart=/usr/bin/x11vnc -auth guess -forever -loop -noxdamage -repeat -rfbauth /home/charger/.vnc/passwd -rfbport 5900 -shared

[Install]
WantedBy=multi-user.target

```

- x11vnc.service 데몬 실행되는 거까지 확인했는데 윈도우에서 VNC Viewer 툴로 접속해봤더니 접속 X, 로컬에서 GUI 환경으로 x11 server 실행시키면 작동된다 그러나 프로세스 확인하니 service 스크립트에 써준 내용이랑 다름

- 참고 사이트

  - [https://www.it-swarm.dev/ko/server/%EA%B7%B8%EB%9E%98%ED%94%BD-%EB%A1%9C%EA%B7%B8%EC%9D%B8-%ED%99%94%EB%A9%B4%EC%9C%BC%EB%A1%9C-%EC%95%A1%EC%84%B8%EC%8A%A4%ED%95%98%EA%B8%B0-%EC%9C%84%ED%95%B4-x11vnc%EB%A5%BC-%EC%84%A4%EC%A0%95%ED%95%98%EB%8A%94-%EB%B0%A9%EB%B2%95%EC%9D%80-%EB%AC%B4%EC%97%87%EC%9E%85%EB%8B%88%EA%B9%8C/961435785/](https://www.it-swarm.dev/ko/server/그래픽-로그인-화면으로-액세스하기-위해-x11vnc를-설정하는-방법은-무엇입니까/961435785/)

  - https://unix.stackexchange.com/questions/402201/creating-x11vnc-system-service
  - https://help.ubuntu.com/community/VNC/Servers#Have_x11vnc_start_automatically_via_systemd_in_any_environment_.28Vivid.2B-.29
  - https://www.neowin.net/forum/topic/1216543-starting-x11vnc-on-boot/

- Q & A

```
안녕하세요 데비안 리눅스 사용중인데 x11vnc 설정관련해서 질문 드립니다
부팅후 자동실행 관련해서 x11vnc.service 데몬 실행되는 거까지 확인했는데 
윈도우에서 VNC Viewer 툴로 접속해봤더니 접속이 안되요
근대 로컬서버에서 GUI 환경으로 x11 server 실행시키면 작동이 되요 
그래서 프로세스 확인하니 service 스크립트에 써준 내용이랑 다른데 어떤 문제일까요?
```

```
혼자 찾아보고 설정하다가 도저히 모르겠어서 데비안 데스크탑 10버전 깔아서 GUI 프로그램 올려야되서 그놈 그래픽으로 사용하고있는데 원격 모니터링 하려고 x11vnc 설치하고 부팅하면 자동실행하게끔 /lib/systemd/system에 service데몬 파일설정했고 수동으로 x11vnc 키면 로컬접속이 되는데 부팅하고 켜보면 접속이 안되는데 구글에 하라는데로 해봤는데도 안되가지고 혹시 어떤 방법이 있을까요? 
```



- 5/13
  - https://forum.ubuntuusers.de/topic/keine-verbindung-mehr-zu-x11vnc-vnc/

***

###  프로그램 내 가상키보드 실행

