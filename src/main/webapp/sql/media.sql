-- media.sql


-- 미디어 테이블 생성
CREATE TABLE media (
  mediano  NUMBER        NOT NULL PRIMARY KEY,          -- 미디어 번호
  title    VARCHAR2(255) NOT NULL,                      -- 제목
  poster   VARCHAR2(255) DEFAULT 'poster.jpg' NOT NULL, -- 포스터 이미지
  filename VARCHAR2(255) NOT NULL,                      -- 음원 파일명
  filesize NUMBER        DEFAULT 0 NOT NULL,
  mview    CHAR(1)       DEFAULT 'Y' NOT NULL,          -- 출력모드
  rdate    DATE          NOT NULL,                      -- 등록일
  mediagroupno NUMBER NULL                              -- 부모테이블 PK
);

-- 행 추가
INSERT INTO media(mediano, title, poster, filename, filesize, mview, rdate, mediagroupno) 
VALUES((SELECT NVL(MAX(mediano), 0)+1 FROM media), ?, ?, ?, ?, ?, ?, ?)
;


-- 목록 조회
SELECT mediano, title, poster, filename, filesize, mview, rdate, mediagroupno FROM media
ORDER BY mediano DESC
;


SELECT mediano, title, poster, filename, filesize, mview, rdate, mediagroupno FROM media
WHERE mview='Y' AND mediagroupno=?
ORDER BY mediano DESC
;

SELECT mediano, title, poster, filename, filesize, mview, rdate, mediagroupno FROM media
WHERE mview='Y' AND mediagroupno=9
ORDER BY mediano DESC
;




INSERT INTO media(mediano, title, poster, filename, filesize, mview, rdate, mediagroupno) 
VALUES((SELECT NVL(MAX(mediano), 0)+1 FROM media), '테스트', 'test.jpg', 'test.mp3', 3000, 'Y', sysdate, 9)
;

INSERT INTO media(mediano, title, poster, filename, filesize, mview, rdate, mediagroupno) 
VALUES((SELECT NVL(MAX(mediano), 0)+1 FROM media), '이태원 프리덤', 'itaewonfreedom.jpg', 'itaewonfreedom.mp3', 5969271, 'Y', sysdate, 1)
;


UPDATE media
SET title='test3', poster='(2)crayonpop.jpg', filename='(2)crayonpop.mp4', filesize=48130545, rdate=sysdate
WHERE mediano=22
;

SELECT mediano, title, poster, filename, filesize, mview, rdate, mediagroupno 
FROM media 
WHERE mview='Y' AND mediano='test3' 
UPDATE media 
SET title='(7)candy.jpg', poster='(7)candy.mp3', filename=9071425, filesize=22, rdate=sysdate 
WHERE mediano=null


