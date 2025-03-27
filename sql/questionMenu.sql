-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('题目管理', '3', '1', 'question', 'question/question/index', 1, 0, 'C', '0', '0', 'question:question:list', '#', 'admin', sysdate(), '', null, '题目管理菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('题目管理查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'question:question:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('题目管理新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'question:question:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('题目管理修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'question:question:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('题目管理删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'question:question:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('题目管理导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'question:question:export',       '#', 'admin', sysdate(), '', null, '');