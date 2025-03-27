-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('用户顺序练习记录', '2068', '1', 'history', 'question/history/index', 1, 0, 'C', '0', '0', 'question:history:list', '#', 'admin', sysdate(), '', null, '用户顺序练习记录菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('用户顺序练习记录查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'question:history:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('用户顺序练习记录新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'question:history:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('用户顺序练习记录修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'question:history:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('用户顺序练习记录删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'question:history:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('用户顺序练习记录导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'question:history:export',       '#', 'admin', sysdate(), '', null, '');