-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('用户考卷记录', '2049', '1', 'exampaper', 'customer/exampaper/index', 1, 0, 'C', '0', '0', 'customer:exampaper:list', '#', 'admin', sysdate(), '', null, '用户考卷记录菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('用户考卷记录查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'customer:exampaper:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('用户考卷记录新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'customer:exampaper:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('用户考卷记录修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'customer:exampaper:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('用户考卷记录删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'customer:exampaper:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('用户考卷记录导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'customer:exampaper:export',       '#', 'admin', sysdate(), '', null, '');