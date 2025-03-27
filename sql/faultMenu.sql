-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('用户错题表', '2068', '1', 'fault', 'customer/fault/index', 1, 0, 'C', '0', '0', 'customer:fault:list', '#', 'admin', sysdate(), '', null, '用户错题表菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('用户错题表查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'customer:fault:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('用户错题表新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'customer:fault:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('用户错题表修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'customer:fault:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('用户错题表删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'customer:fault:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('用户错题表导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'customer:fault:export',       '#', 'admin', sysdate(), '', null, '');