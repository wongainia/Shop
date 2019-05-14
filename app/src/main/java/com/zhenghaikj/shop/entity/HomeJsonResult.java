package com.zhenghaikj.shop.entity;

import java.io.Serializable;
import java.util.List;

public class HomeJsonResult implements Serializable {


    /**
     * page : {}
     * PModules : []
     * LModules : [{"id":"201951413538327","type":9,"draggable":true,"sort":0,"content":{"showType":"1","space":0,"margin":10,"dataset":[{"linkType":0,"link":"#","pc_link":"#","title":"导航名称","pic":"http://mall.xigyu.com//Storage/template/0/20190514/6369343882050914528698137.jpg"},{"linkType":0,"link":"#","pc_link":"#","title":"导航名称","pic":"http://mall.xigyu.com//Storage/template/0/20190514/6369343881958726612612880.png"},{"linkType":0,"link":"#","pc_link":"#","title":"导航名称","pic":"http://mall.xigyu.com//Storage/template/0/20190514/6369343881980602681611346.jpg"},{"linkType":0,"link":"","title":"","showtitle":"","pic":"http://mall.xigyu.com//Storage/template/0/20190514/6369343881997790349325733.jpg"},{"linkType":0,"link":"","title":"","showtitle":"","pic":"http://mall.xigyu.com//Storage/template/0/20190514/6369343882011850566283456.jpg"},{"linkType":0,"link":"","title":"","showtitle":"","pic":"http://mall.xigyu.com//Storage/template/0/20190514/6369343882022788885782689.jpg"}]},"dom_conitem":"CiAgICAgICAgICAgIDxkaXYgY2xhc3M9Im1lbWJlcnNfY29uIiBzdHlsZT0ibWFyZ2luOjAgYXV0byI+CiAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgIDxzZWN0aW9uIGNsYXNzPSJtZW1iZXJzX2ZsYXNoIGotc3dpcGUiIGlkPSJteVN3aXBlIj4KICAgICAgICAgICAgICAgICAgICA8dWwgY2xhc3M9ImNsZWFyZml4Ij4KICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICA8bGk+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICA8YSBocmVmPSIjIiB0aXRsZT0iIj4KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8aW1nIHNyYz0iaHR0cDovL21hbGwueGlneXUuY29tLy9TdG9yYWdlL3RlbXBsYXRlLzAvMjAxOTA1MTQvNjM2OTM0Mzg4MjA1MDkxNDUyODY5ODEzNy5qcGciIHdpZHRoPSIxMDAlIiAvPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgPC9hPgogICAgICAgICAgICAgICAgICAgICAgICA8L2xpPgogICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgPGxpPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgPGEgaHJlZj0iIyIgdGl0bGU9IiI+DxzZWN0aW9uIGNsYXNzPSJtZW1iZXJzX2ZsYXNoX3RpbWUiPgogICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgIDxzcGFuIGNsYXNzPSJjdXIiID48L3NwYW4+CiAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICA8c3BhbiAgPjwvc3Bhbj4KICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgIDxzcGFuICA+PC9zcGFuPgogICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgPHNwYW4gID48L3NwYW4+CiAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICA8c3BhbiAgPjwvc3Bhbj4KICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgIDxzcGFuICA+PC9zcGFuPgogICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgPC9zZWN0aW9uPgogICAgICAgICAgICAgICAgPC9zZWN0aW9uPgogICAgICAgICAgICAgICAgCiAgICAgICAgICAgIDwvZGl2PgogICAgICAgIA==","dom_ctrl":null,"ue":null},{"id":"2019514141118596","type":8,"draggable":true,"sort":1,"content":{"dataset":[{"linkType":0,"link":"#","title":"导航名称","pic":"/Content/Public/images/diy/app/group_120_120.png","picHeight":0},{"linkType":0,"link":"#","title":"导航名称","pic":"/Content/Public/images/diy/app/group_120_120.png","picHeight":0},{"linkType":0,"link":"#","title":"导航名称","pic":"/Content/Public/images/diy/app/group_120_120.png","picHeight":0},{"linkType":0,"link":"#","title":"导航名称","pic":"/Content/Public/images/diy/app/group_120_120.png","picHeight":0}]},
     * "dom_conitem":"++++++PC9kaXY+++PGRpdiBjbGFzcz0ic29uIj48aW1nIHNyYz0iL0NvbnRlbnQvUHVibGljL2ltYWdlcy9kaXkvYXBwL2dyb3VwXzEyMF8xMjAucG5nIj48L2Rpdj48L2E+PC9zcGFuPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgPGEgY2xhc3M9Im1lbWJlcnNfbmF2MV9uYW1lIiBocmVmPSIjIj48L2E+CiAgICAgICAgICAgICAgICAgICAgICAgIDwvbGk+CiAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgIDxsaSBjbGFzcz0ibGlzdzQiPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgPHNwYW4+PGEgY2xhc3M9Imdvb2RzaW1nIiBocmVmPSIjIj48ZGl2IGNsYXNzPSJzb24iPjxpbWcgc3JjPSIvQ29udGVudC9QdWJsaWMvaW1hZ2VzL2RpeS9hcHAvZ3JvdXBfMTIwXzEyMC5wbmciPjwvZGl2PjwvYT48L3NwYW4+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICA8YSBjbGFzcz0ibWVtYmVyc19uYXYxX25hbWUiIGhyZWY9IiMiPjwvYT4KICAgICAgICAgICAgICAgICAgICAgICAgPC9saT4KICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICA8L3VsPgogICAgICAgICAgICAgICAgPC9zZWN0aW9uPgogICAgICAgICAgICA8L2Rpdj4KICAgICAgICA=","dom_ctrl":null,"ue":null},{"id":"2019514141123356","type":20,"draggable":true,"sort":2,"content":{"layout":"1","dataset":[{"linkType":0,"link":"#","pc_link":"#","title":"导航名称","pic":"/Content/Public/images/diy/app/bar01.png"},{"linkType":0,"link":"#","pc_link":"#","title":"导航名称","pic":"/Content/Public/images/diy/app/group_1_2.png"},{"linkType":0,"link":"#","pc_link":"#","title":"导航名称","pic":"/Content/Public/images/diy/app/group_1_3.png"},{"linkType":0,"link":"#","pc_link":"#","title":"导航名称","pic":"/Content/Public/images/diy/app/group_1_4.png"},{"linkType":0,"link":"#","pc_link":"#","title":"导航名称","pic":"/Content/Public/images/diy/app/group_1_5.png"}]},"dom_conitem":"CiAgICAgICAgICAgIDxkaXYgY2xhc3M9Im1lbWJlcnNfY29uIj4KICAgICAgICAgICAgICAgIDxzZWN0aW9uIGNsYXNzPSJtZW1iZXJzX25hdjEiPgogICAgICAgICAgICAgICAgICAgIDx1bCBjbGFzcz0iY2xlYXJmaXgiPgogICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICA8bGkgY2xhc3M9ImJvYXJkNSB0aXRsZV9pbWciPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgPHNwYW4+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgPGEgaHJlZj0iIyI+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8aW1nIHNyYz0iL0NvbnRlbnQvUHVibGljL2ltYWdlcy9kaXkvYXBwL2JhcjAxLnBuZyI+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIDwvYT4KICAgICAgICAgICAgICAgICAgICAgICAgICAgIDwvc3Bhbj4KICAgICAgICAgICAgICAgICAgICAgICAgPC9saT4KICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgPGxpIGNsYXNzPSJib2FyZDUgYmlnX2ltZyI+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICA8c3Bhbj4KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8YSBocmVmPSIjIj4KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIDxpbWcgc3JjPSIvQ29udGVudC9QdWJsaWMvaW1hZ2VzL2RpeS9hcHAvZ3JvdXBfMV8yLnBuZyI+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIDwvYT4KICAgICAgICAgICAgICAgICAgICAgICAgICAgIDwvc3Bhbj4KICAgICAgICAgICAgICAgICAgICAgICAgPC9saT4KICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgPGxpIGNsYXNzPSJib2FyZDUgbWlkX2ltZyI+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICA8c3Bhbj4KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8YSBocmVmPSIjIj4KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIDxpbWcgc3JjPSIvQ29udGVudC9QdWJsaWMvaW1hZ2VzL2RpeS9hcHAvZ3JvdXBfMV8zLnBuZyI+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIDwvYT4KICAgICAgICAgICAgICAgICAgICAgICAgICAgIDwvc3Bhbj4KICAgICAgICAgICAgICAgICAgICAgICAgPC9saT4KICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgPGxpIGNsYXNzPSJib2FyZDUgc21hbGxfaW1nIj4KICAgICAgICAgICAgICAgICAgICAgICAgICAgIDxzcGFuPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIDxhIGhyZWY9IiMiPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgPGltZyBzcmM9Ii9Db250ZW50L1B1YmxpYy9pbWFnZXMvZGl5L2FwcC9ncm91cF8xXzQucG5nIj4KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgPC9hPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgPC9zcGFuPgogICAgICAgICAgICAgICAgICAgICAgICA8L2xpPgogICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICA8bGkgY2xhc3M9ImJvYXJkNSBzbWFsbF9pbWciPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgPHNwYW4+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgPGEgaHJlZj0iIyI+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8aW1nIHNyYz0iL0NvbnRlbnQvUHVibGljL2ltYWdlcy9kaXkvYXBwL2dyb3VwXzFfNS5wbmciPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8L2E+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICA8L3NwYW4+CiAgICAgICAgICAgICAgICAgICAgICAgIDwvbGk+CiAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgPC91bD4KICAgICAgICAgICAgICAgIDwvc2VjdGlvbj4KICAgICAgICAgICAgPC9kaXY+CiAgICAgICAg","dom_ctrl":null,"ue":null},{"id":"201951414112430","type":21,"draggable":true,"sort":3,"content":{"layout":"1","dataset":[{"linkType":0,"link":"#","pc_link":"#","title":"导航名称","pic":"/Content/Public/images/diy/app/bar01.png"},{"linkType":0,"link":"#","pc_link":"#","title":"导航名称","pic":"/Content/Public/images/diy/app/group_2_2_custom.png"},{"linkType":0,"link":"#","pc_link":"#","title":"导航名称","pic":"/Content/Public/images/diy/app/group_2_3_custom.png"},{"linkType":0,"link":"#","pc_link":"#","title":"导航名称","pic":"/Content/Public/images/diy/app/group_2_4_custom.png"},{"linkType":0,"link":"#","pc_link":"#","title":"导航名称","pic":"/Content/Public/images/diy/app/group_2_5_custom.png"},{"linkType":0,"link":"#","pc_link":"#","title":"导航名称","pic":"/Content/Public/images/diy/app/group_2_6_custom.png"},{"linkType":0,"link":"#","pc_link":"#","title":"导航名称","pic":"/Content/Public/images/diy/app/group_2_7_custom.png"}]},"dom_conitem":"CiAgICAgICAgICAgIDxkaXYgY2xhc3M9Im1lbWJlcnNfY29uIj4KICAgICAgICAgICAgICAgIDxzZWN0aW9uIGNsYXNzPSJtZW1iZXJzX25hdjEiPgogICAgICAgICAgICAgICAgICAgIDx1bCBjbGFzcz0iY2xlYXJmaXgiPgogICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICA8bGkgY2xhc3M9ImJvYXJkNyB0aXRsZV9pbWciPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgPHNwYW4+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgPGEgaHJlZj0iIyI+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8aW1nIHNyYz0iL0NvbnRlbnQvUHVibGljL2ltYWdlcy9kaXkvYXBwL2JhcjAxLnBuZyI+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIDwvYT4KICAgICAgICAgICAgICAgICAgICAgICAgICAgIDwvc3Bhbj4KICAgICAgICAgICAgICAgICAgICAgICAgPC9saT4KICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgPGxpIGNsYXNzPSJib2FyZDcgbWlkMV9pbWciPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgPHNwYW4+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgPGEgaHJlZj0iIyI+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8aW1nIHNyYz0iL0NvbnRlbnQvUHVibGljL2ltYWdlcy9kaXkvYXBwL2dyb3VwXzJfMl9jdXN0b20ucG5nIj4KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgPC9hPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgPC9zcGFuPgogICAgICAgICAgICAgICAgICAgICAgICA8L2xpPgogICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICA8bGkgY2xhc3M9ImJvYXJkNyBzbWFsbDFfaW1nIj4KICAgICAgICAgICAgICAgICAgICAgICAgICAgIDxzcGFuPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIDxhIGhyZWY9IiMiPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgPGltZyBzcmM9Ii9Db250ZW50L1B1YmxpYy9pbWFnZXMvZGl5L2FwcC9ncm91cF8yXzNfY3VzdG9tLnBuZyI+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIDwvYT4KICAgICAgICAgICAgICAgICAgICAgICAgICAgIDwvc3Bhbj4KICAgICAgICAgICAgICAgICAgICAgICAgPC9saT4KICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgPGxpIGNsYXNzPSJib2FyZDcgc21hbGwxX2ltZyI+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICA8c3Bhbj4KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8YSBocmVmPSIjIj4KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIDxpbWcgc3JjPSIvQ29udGVudC9QdWJsaWMvaW1hZ2VzL2RpeS9hcHAvZ3JvdXBfMl80X2N1c3RvbS5wbmciPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8L2E+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICA8L3NwYW4+CiAgICAgICAgICAgICAgICAgICAgICAgIDwvbGk+CiAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgIDxsaSBjbGFzcz0iYm9hcmQ3IG1pZDFfaW1nIj4KICAgICAgICAgICAgICAgICAgICAgICAgICAgIDxzcGFuPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIDxhIGhyZWY9IiMiPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgPGltZyBzcmM9Ii9Db250ZW50L1B1YmxpYy9pbWFnZXMvZGl5L2FwcC9ncm91cF8yXzVfY3VzdG9tLnBuZyI+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIDwvYT4KICAgICAgICAgICAgICAgICAgICAgICAgICAgIDwvc3Bhbj4KICAgICAgICAgICAgICAgICAgICAgICAgPC9saT4KICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgPGxpIGNsYXNzPSJib2FyZDcgc21hbGwxX2ltZyI+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICA8c3Bhbj4KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8YSBocmVmPSIjIj4KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIDxpbWcgc3JjPSIvQ29udGVudC9QdWJsaWMvaW1hZ2VzL2RpeS9hcHAvZ3JvdXBfMl82X2N1c3RvbS5wbmciPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8L2E+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICA8L3NwYW4+CiAgICAgICAgICAgICAgICAgICAgICAgIDwvbGk+CiAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgIDxsaSBjbGFzcz0iYm9hcmQ3IHNtYWxsMV9pbWciPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgPHNwYW4+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgPGEgaHJlZj0iIyI+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8aW1nIHNyYz0iL0NvbnRlbnQvUHVibGljL2ltYWdlcy9kaXkvYXBwL2dyb3VwXzJfN19jdXN0b20ucG5nIj4KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgPC9hPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgPC9zcGFuPgogICAgICAgICAgICAgICAgICAgICAgICA8L2xpPgogICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgIDwvdWw+CiAgICAgICAgICAgICAgICA8L3NlY3Rpb24+CiAgICAgICAgICAgIDwvZGl2PgogICAgICAgIA==","dom_ctrl":null,"ue":null},{"id":"2019514141124660","type":22,"draggable":true,"sort":4,"content":{"layout":"1","dataset":[{"linkType":0,"link":"#","pc_link":"#","title":"导航名称","pic":"/Content/Public/images/diy/app/bar01.png"},{"linkType":0,"link":"#","pc_link":"#","title":"导航名称","pic":"/Content/Public/images/diy/app/group_3_2.png"},{"linkType":0,"link":"#","pc_link":"#","title":"导航名称","pic":"/Content/Public/images/diy/app/group_3_3.png"},{"linkType":0,"link":"#","pc_link":"#","title":"导航名称","pic":"/Content/Public/images/diy/app/group_3_4.png"},{"linkType":0,"link":"#","pc_link":"#","title":"导航名称","pic":"/Content/Public/images/diy/app/group_3_5.png"},{"linkType":0,"link":"#","pc_link":"#","title":"导航名称","pic":"/Content/Public/images/diy/app/group_3_6.png"},{"linkType":0,"link":"#","pc_link":"#","title":"导航名称","pic":"/Content/Public/images/diy/app/group_3_7.png"}]},"dom_conitem":"CiAgICAgICAgICAgIDxkaXYgY2xhc3M9Im1lbWJlcnNfY29uIj4KICAgICAgICAgICAgICAgIDxzZWN0aW9uIGNsYXNzPSJtZW1iZXJzX25hdjEiPgogICAgICAgICAgICAgICAgICAgIDx1bCBjbGFzcz0iY2xlYXJmaXgiPgogICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICA8bGkgY2xhc3M9ImJvYXJkNyB0aXRsZV9pbWciPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgPHNwYW4+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgPGEgaHJlZj0iIyI+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8aW1nIHNyYz0iL0NvbnRlbnQvUHVibGljL2ltYWdlcy9kaXkvYXBwL2JhcjAxLnBuZyI+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIDwvYT4KICAgICAgICAgICAgICAgICAgICAgICAgICAgIDwvc3Bhbj4KICAgICAgICAgICAgICAgICAgICAgICAgPC9saT4KICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgPGxpIGNsYXNzPSJib2FyZDcgYmlnMV9pbWciPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgPHNwYW4+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgPGEgaHJlZj0iIyI+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8aW1nIHNyYz0iL0NvbnRlbnQvUHVibGljL2ltYWdlcy9kaXkvYXBwL2dyb3VwXzNfMi5wbmciPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8L2E+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICA8L3NwYW4+CiAgICAgICAgICAgICAgICAgICAgICAgIDwvbGk+CiAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgIDxsaSBjbGFzcz0iYm9hcmQ3IGJpZzFfaW1nIj4KICAgICAgICAgICAgICAgICAgICAgICAgICAgIDxzcGFuPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIDxhIGhyZWY9IiMiPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgPGltZyBzcmM9Ii9Db250ZW50L1B1YmxpYy9pbWFnZXMvZGl5L2FwcC9ncm91cF8zXzMucG5nIj4KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgPC9hPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgPC9zcGFuPgogICAgICAgICAgICAgICAgICAgICAgICA8L2xpPgogICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICA8bGkgY2xhc3M9ImJvYXJkNyBiaWcxX2ltZyI+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICA8c3Bhbj4KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8YSBocmVmPSIjIj4KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIDxpbWcgc3JjPSIvQ29udGVudC9QdWJsaWMvaW1hZ2VzL2RpeS9hcHAvZ3JvdXBfM180LnBuZyI+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIDwvYT4KICAgICAgICAgICAgICAgICAgICAgICAgICAgIDwvc3Bhbj4KICAgICAgICAgICAgICAgICAgICAgICAgPC9saT4KICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgPGxpIGNsYXNzPSJib2FyZDcgYmlnMV9pbWciPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgPHNwYW4+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgPGEgaHJlZj0iIyI+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8aW1nIHNyYz0iL0NvbnRlbnQvUHVibGljL2ltYWdlcy9kaXkvYXBwL2dyb3VwXzNfNS5wbmciPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8L2E+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICA8L3NwYW4+CiAgICAgICAgICAgICAgICAgICAgICAgIDwvbGk+CiAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgIDxsaSBjbGFzcz0iYm9hcmQ3IGJpZzFfaW1nIj4KICAgICAgICAgICAgICAgICAgICAgICAgICAgIDxzcGFuPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIDxhIGhyZWY9IiMiPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgPGltZyBzcmM9Ii9Db250ZW50L1B1YmxpYy9pbWFnZXMvZGl5L2FwcC9ncm91cF8zXzYucG5nIj4KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgPC9hPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgPC9zcGFuPgogICAgICAgICAgICAgICAgICAgICAgICA8L2xpPgogICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICA8bGkgY2xhc3M9ImJvYXJkNyBiaWcxX2ltZyI+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICA8c3Bhbj4KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8YSBocmVmPSIjIj4KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIDxpbWcgc3JjPSIvQ29udGVudC9QdWJsaWMvaW1hZ2VzL2RpeS9hcHAvZ3JvdXBfM183LnBuZyI+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIDwvYT4KICAgICAgICAgICAgICAgICAgICAgICAgICAgIDwvc3Bhbj4KICAgICAgICAgICAgICAgICAgICAgICAgPC9saT4KICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICA8L3VsPgogICAgICAgICAgICAgICAgPC9zZWN0aW9uPgogICAgICAgICAgICA8L2Rpdj4KICAgICAgICA=","dom_ctrl":null,"ue":null},{"id":"2019514141125205","type":23,"draggable":true,"sort":5,"content":{"layout":"1","dataset":[{"linkType":0,"link":"#","pc_link":"#","title":"导航名称","pic":"/Content/Public/images/diy/app/bar01.png"},{"linkType":0,"link":"#","pc_link":"#","title":"导航名称","pic":"/Content/Public/images/diy/app/group_4_2.png"},{"linkType":0,"link":"#","pc_link":"#","title":"导航名称","pic":"/Content/Public/images/diy/app/group_4_3.png"},{"linkType":0,"link":"#","pc_link":"#","title":"导航名称","pic":"/Content/Public/images/diy/app/group_4_4.png"},{"linkType":0,"link":"#","pc_link":"#","title":"导航名称","pic":"/Content/Public/images/diy/app/group_4_5.png"},{"linkType":0,"link":"#","pc_link":"#","title":"导航名称","pic":"/Content/Public/images/diy/app/group_4_6.png"},{"linkType":0,"link":"#","pc_link":"#","title":"导航名称","pic":"/Content/Public/images/diy/app/group_4_7.png"},{"linkType":0,"link":"#","pc_link":"#","title":"导航名称","pic":"/Content/Public/images/diy/app/group_4_8.png"},{"linkType":0,"link":"#","pc_link":"#","title":"导航名称","pic":"/Content/Public/images/diy/app/group_4_9.png"}]},"dom_conitem":"CiAgICAgICAgICAgIDxkaXYgY2xhc3M9Im1lbWJlcnNfY29uIj4KICAgICAgICAgICAgICAgIDxzZWN0aW9uIGNsYXNzPSJtZW1iZXJzX25hdjEiPgogICAgICAgICAgICAgICAgICAgIDx1bCBjbGFzcz0iY2xlYXJmaXgiPgogICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICA8bGkgY2xhc3M9ImJvYXJkOSB0aXRsZV9pbWciPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgPHNwYW4+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgPGEgaHJlZj0iIyI+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8aW1nIHNyYz0iL0NvbnRlbnQvUHVibGljL2ltYWdlcy9kaXkvYXBwL2JhcjAxLnBuZyI+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIDwvYT4KICAgICAgICAgICAgICAgICAgICAgICAgICAgIDwvc3Bhbj4KICAgICAgICAgICAgICAgICAgICAgICAgPC9saT4KICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgPGxpIGNsYXNzPSJib2FyZDkgYmlnMV9pbWciPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgPHNwYW4+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgPGEgaHJlZj0iIyI+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8aW1nIHNyYz0iL0NvbnRlbnQvUHVibGljL2ltYWdlcy9kaXkvYXBwL2dyb3VwXzRfMi5wbmciPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8L2E+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICA8L3NwYW4+CiAgICAgICAgICAgICAgICAgICAgICAgIDwvbGk+CiAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgIDxsaSBjbGFzcz0iYm9hcmQ5IGJpZzFfaW1nIj4KICAgICAgICAgICAgICAgICAgICAgICAgICAgIDxzcGFuPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIDxhIGhyZWY9IiMiPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgPGltZyBzcmM9Ii9Db250ZW50L1B1YmxpYy9pbWFnZXMvZGl5L2FwcC9ncm91cF80XzMucG5nIj4KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgPC9hPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgPC9zcGFuPgogICAgICAgICAgICAgICAgICAgICAgICA8L2xpPgogICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICA8bGkgY2xhc3M9ImJvYXJkOSBiaWcxX2ltZyI+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICA8c3Bhbj4KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8YSBocmVmPSIjIj4KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIDxpbWcgc3JjPSIvQ29udGVudC9QdWJsaWMvaW1hZ2VzL2RpeS9hcHAvZ3JvdXBfNF80LnBuZyI+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIDwvYT4KICAgICAgICAgICAgICAgICAgICAgICAgICAgIDwvc3Bhbj4KICAgICAgICAgICAgICAgICAgICAgICAgPC9saT4KICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgPGxpIGNsYXNzPSJib2FyZDkgYmlnMV9pbWciPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgPHNwYW4+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgPGEgaHJlZj0iIyI+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8aW1nIHNyYz0iL0NvbnRlbnQvUHVibGljL2ltYWdlcy9kaXkvYXBwL2dyb3VwXzRfNS5wbmciPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8L2E+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICA8L3NwYW4+CiAgICAgICAgICAgICAgICAgICAgICAgIDwvbGk+CiAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgIDxsaSBjbGFzcz0iYm9hcmQ5IHNtYWxsMl9pbWciPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgPHNwYW4+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgPGEgaHJlZj0iIyI+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8aW1nIHNyYz0iL0NvbnRlbnQvUHVibGljL2ltYWdlcy9kaXkvYXBwL2dyb3VwXzRfNi5wbmciPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8L2E+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICA8L3NwYW4+CiAgICAgICAgICAgICAgICAgICAgICAgIDwvbGk+CiAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgIDxsaSBjbGFzcz0iYm9hcmQ5IHNtYWxsMl9pbWciPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgPHNwYW4+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgPGEgaHJlZj0iIyI+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8aW1nIHNyYz0iL0NvbnRlbnQvUHVibGljL2ltYWdlcy9kaXkvYXBwL2dyb3VwXzRfNy5wbmciPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8L2E+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICA8L3NwYW4+CiAgICAgICAgICAgICAgICAgICAgICAgIDwvbGk+CiAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgIDxsaSBjbGFzcz0iYm9hcmQ5IHNtYWxsMl9pbWciPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgPHNwYW4+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgPGEgaHJlZj0iIyI+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8aW1nIHNyYz0iL0NvbnRlbnQvUHVibGljL2ltYWdlcy9kaXkvYXBwL2dyb3VwXzRfOC5wbmciPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8L2E+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICA8L3NwYW4+CiAgICAgICAgICAgICAgICAgICAgICAgIDwvbGk+CiAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgIDxsaSBjbGFzcz0iYm9hcmQ5IHNtYWxsMl9pbWciPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgPHNwYW4+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgPGEgaHJlZj0iIyI+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8aW1nIHNyYz0iL0NvbnRlbnQvUHVibGljL2ltYWdlcy9kaXkvYXBwL2dyb3VwXzRfOS5wbmciPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8L2E+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICA8L3NwYW4+CiAgICAgICAgICAgICAgICAgICAgICAgIDwvbGk+CiAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgPC91bD4KICAgICAgICAgICAgICAgIDwvc2VjdGlvbj4KICAgICAgICAgICAgPC9kaXY+CiAgICAgICAg","dom_ctrl":null,"ue":null},{"id":"2019514141125747","type":24,"draggable":true,"sort":6,"content":{"layout":"1","dataset":[{"linkType":0,"link":"#","pc_link":"#","title":"导航名称","pic":"/Content/Public/images/diy/app/ad.png","picHeight":0}]},"dom_conitem":"CiAgICAgICAgICAgIDxkaXYgY2xhc3M9Im1lbWJlcnNfY29uIj4KICAgICAgICAgICAgICAgIDxzZWN0aW9uIGNsYXNzPSJtZW1iZXJzX25hdjEiPgogICAgICAgICAgICAgICAgICAgIDx1bCBjbGFzcz0iY2xlYXJmaXgiPgogICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICA8bGkgY2xhc3M9ImJvYXJkIGFkX2ltZyI+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICA8c3Bhbj4KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8YSBocmVmPSIjIj4KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIDxpbWcgc3JjPSIvQ29udGVudC9QdWJsaWMvaW1hZ2VzL2RpeS9hcHAvYWQucG5nIj4KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgPC9hPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgPC9zcGFuPgogICAgICAgICAgICAgICAgICAgICAgICA8L2xpPgogICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgIDwvdWw+CiAgICAgICAgICAgICAgICA8L3NlY3Rpb24+CiAgICAgICAgICAgIDwvZGl2PgogICAgICAgIA==",
     * "dom_ctrl":null,"ue":null}]
     */

    private PageBean page;
    private List<?> PModules;
    private List<LModulesBean> LModules;

    public PageBean getPage() {
        return page;
    }

    public void setPage(PageBean page) {
        this.page = page;
    }

    public List<?> getPModules() {
        return PModules;
    }

    public void setPModules(List<?> PModules) {
        this.PModules = PModules;
    }

    public List<LModulesBean> getLModules() {
        return LModules;
    }

    public void setLModules(List<LModulesBean> LModules) {
        this.LModules = LModules;
    }

    public static class PageBean {
    }

    public static class LModulesBean {
        /**
         * id : 201951413538327
         * type : 9
         * draggable : true
         * sort : 0
         * content : {"showType":"1","space":0,"margin":10,"dataset":[{"linkType":0,"link":"#","pc_link":"#","title":"导航名称","pic":"http://mall.xigyu.com//Storage/template/0/20190514/6369343882050914528698137.jpg"},{"linkType":0,"link":"#","pc_link":"#","title":"导航名称","pic":"http://mall.xigyu.com//Storage/template/0/20190514/6369343881958726612612880.png"},{"linkType":0,"link":"#","pc_link":"#","title":"导航名称","pic":"http://mall.xigyu.com//Storage/template/0/20190514/6369343881980602681611346.jpg"},{"linkType":0,"link":"","title":"","showtitle":"","pic":"http://mall.xigyu.com//Storage/template/0/20190514/6369343881997790349325733.jpg"},{"linkType":0,"link":"","title":"","showtitle":"","pic":"http://mall.xigyu.com//Storage/template/0/20190514/6369343882011850566283456.jpg"},{"linkType":0,"link":"","title":"","showtitle":"","pic":"http://mall.xigyu.com//Storage/template/0/20190514/6369343882022788885782689.jpg"}]}
         * dom_conitem :
         * dom_ctrl : null
         * ue : null
         */

        private String id;
        private int type;
        private boolean draggable;
        private int sort;
        private ContentBean content;
        private String dom_conitem;
        private Object dom_ctrl;
        private Object ue;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public boolean isDraggable() {
            return draggable;
        }

        public void setDraggable(boolean draggable) {
            this.draggable = draggable;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public ContentBean getContent() {
            return content;
        }

        public void setContent(ContentBean content) {
            this.content = content;
        }

        public String getDom_conitem() {
            return dom_conitem;
        }

        public void setDom_conitem(String dom_conitem) {
            this.dom_conitem = dom_conitem;
        }

        public Object getDom_ctrl() {
            return dom_ctrl;
        }

        public void setDom_ctrl(Object dom_ctrl) {
            this.dom_ctrl = dom_ctrl;
        }

        public Object getUe() {
            return ue;
        }

        public void setUe(Object ue) {
            this.ue = ue;
        }

        public static class ContentBean {
            /**
             * showType : 1
             * space : 0
             * margin : 10
             * dataset : [{"linkType":0,"link":"#","pc_link":"#","title":"导航名称","pic":"http://mall.xigyu.com//Storage/template/0/20190514/6369343882050914528698137.jpg"},{"linkType":0,"link":"#","pc_link":"#","title":"导航名称","pic":"http://mall.xigyu.com//Storage/template/0/20190514/6369343881958726612612880.png"},{"linkType":0,"link":"#","pc_link":"#","title":"导航名称","pic":"http://mall.xigyu.com//Storage/template/0/20190514/6369343881980602681611346.jpg"},{"linkType":0,"link":"","title":"","showtitle":"","pic":"http://mall.xigyu.com//Storage/template/0/20190514/6369343881997790349325733.jpg"},{"linkType":0,"link":"","title":"","showtitle":"","pic":"http://mall.xigyu.com//Storage/template/0/20190514/6369343882011850566283456.jpg"},{"linkType":0,"link":"","title":"","showtitle":"","pic":"http://mall.xigyu.com//Storage/template/0/20190514/6369343882022788885782689.jpg"}]
             */

            private String showType;
            private int space;
            private int margin;
            private List<DatasetBean> dataset;

            public String getShowType() {
                return showType;
            }

            public void setShowType(String showType) {
                this.showType = showType;
            }

            public int getSpace() {
                return space;
            }

            public void setSpace(int space) {
                this.space = space;
            }

            public int getMargin() {
                return margin;
            }

            public void setMargin(int margin) {
                this.margin = margin;
            }

            public List<DatasetBean> getDataset() {
                return dataset;
            }

            public void setDataset(List<DatasetBean> dataset) {
                this.dataset = dataset;
            }

            public static class DatasetBean {
                /**
                 * linkType : 0
                 * link : #
                 * pc_link : #
                 * title : 导航名称
                 * pic : http://mall.xigyu.com//Storage/template/0/20190514/6369343882050914528698137.jpg
                 * showtitle :
                 */

                private int linkType;
                private String link;
                private String pc_link;
                private String title;
                private String pic;
                private String showtitle;

                public int getLinkType() {
                    return linkType;
                }

                public void setLinkType(int linkType) {
                    this.linkType = linkType;
                }

                public String getLink() {
                    return link;
                }

                public void setLink(String link) {
                    this.link = link;
                }

                public String getPc_link() {
                    return pc_link;
                }

                public void setPc_link(String pc_link) {
                    this.pc_link = pc_link;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getPic() {
                    return pic;
                }

                public void setPic(String pic) {
                    this.pic = pic;
                }

                public String getShowtitle() {
                    return showtitle;
                }

                public void setShowtitle(String showtitle) {
                    this.showtitle = showtitle;
                }
            }
        }
    }
}
