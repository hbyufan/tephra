DROP TABLE IF EXISTS t_dao_auto;
CREATE TABLE t_dao_auto
(
  c_id CHAR(36) NOT NULL COMMENT '主键',
  c_md5 CHAR(32) NOT NULL COMMENT 'MD5值',
  c_data_source VARCHAR(255) DEFAULT NULL COMMENT '数据源',
  c_sql TEXT DEFAULT NULL COMMENT 'SQL',
  c_state INT DEFAULT 0 COMMENT '状态：0-执行；1-失效',
  c_time DATETIME DEFAULT NULL COMMENT '时间',

  PRIMARY KEY pk(c_id) USING HASH,
  KEY k_md5_state(c_md5,c_state) USING HASH
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
