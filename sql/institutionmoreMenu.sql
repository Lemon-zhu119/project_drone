-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('机构信息详情管理', '2061', '1', 'institutionmore', 'institution/institutionmore/index', 1, 0, 'C', '0', '0', 'institution:institutionmore:list', '#', 'admin', sysdate(), '', null, '机构信息详情管理菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('机构信息详情管理查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'institution:institutionmore:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('机构信息详情管理新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'institution:institutionmore:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('机构信息详情管理修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'institution:institutionmore:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('机构信息详情管理删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'institution:institutionmore:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('机构信息详情管理导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'institution:institutionmore:export',       '#', 'admin', sysdate(), '', null, '');