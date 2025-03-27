-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('用户收藏', '2049', '1', 'favor', 'customer/favor/index', 1, 0, 'C', '0', '0', 'customer:favor:list', '#', 'admin', sysdate(), '', null, '用户收藏菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('用户收藏查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'customer:favor:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('用户收藏新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'customer:favor:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('用户收藏修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'customer:favor:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('用户收藏删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'customer:favor:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('用户收藏导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'customer:favor:export',       '#', 'admin', sysdate(), '', null, '');