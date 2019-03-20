

-- mediagroup.sql
-- 미디어 그룹 테이블 생성
CREATE TABLE mediagroup (
mediagroupno NUMBER NOT NULL PRIMARY KEY, 
title VARCHAR2(255) NOT NULL 
)
;

-- 행 추가
-- 그룹번호: 그룹번호 최대값+1
-- 그룹제목: 2018년 댄스 음악
INSERT INTO mediagroup(mediagroupno, title) 
VALUES((SELECT NVL(MAX(mediagroupno), 0)+1 FROM mediagroup), ?)
;

-- 목록 조회
SELECT mediagroupno, title FROM mediagroup
ORDER BY mediagroupno DESC
;


-- 삭제
DELETE FROM mediagroup 
WHERE mediagroupno=?


-- 수정
UPDATE mediagroup 
SET title=?
WHERE mediagroupno=?
;


INSERT INTO mediagroup(mediagroupno, title) 
VALUES((SELECT NVL(MAX(mediagroupno), 0)+1 FROM mediagroup), 'Classic')
;
