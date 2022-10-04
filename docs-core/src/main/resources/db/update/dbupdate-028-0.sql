alter table T_DOCUMENT add column DOC_GPA_C varchar(500);;
-- alter table T_DOCUMENT add column DOC_AGE_C varchar(500);;
-- alter table T_DOCUMENT add column DOC_GENDER_C varchar(500);
-- alter table T_DOCUMENT add column DOC_EXPERIENCE_C varchar(500);;
-- alter table T_DOCUMENT add column DOC_SKILLS_C varchar(500);;
-- alter table T_DOCUMENT add column DOC_PROGRAM_C varchar(500);
update T_CONFIG set CFG_VALUE_C = '28' where CFG_ID_C = 'DB_VERSION';