/**
 * This class is the controller for the main view for the application. It is specified as
 * the "controller" of the Main view class.
 *
 */
Ext.define('yt_manager_app.view.main.MainController', {
    extend: 'Ext.app.ViewController',

    alias: 'controller.main',

    listen: {
        controller: {
            '#': {
                unmatchedroute: 'onRouteChange'
            }
        }
    },

    routes: {
        ':node': 'onRouteChange'
    },

    init: function () {
        var username = localStorage.getItem('yt_manager_app.login.username');
        var model = this.getViewModel(),
            data = model.getData();
        data.username = username;

        var w = window.innerWidth
            || document.documentElement.clientWidth
            || document.body.clientWidth;

        var h = window.innerHeight
            || document.documentElement.clientHeight
            || document.body.clientHeight;
        data.toggle.availWidth = w;
        data.toggle.availHeight = h;

        this.onToggleNavigationSize();
    },

    setCurrentView: function (hashTag) {
        hashTag = (hashTag || '').toLowerCase();

        var model = this.getViewModel(),
            data = model.getData();

        var mainCard = this.lookupReference('main_card'),
            mainLayout = mainCard.getLayout(),
            treeList = this.lookupReference('tree_list'),
            store = treeList.getStore(),
            node = store.findNode('routeId', hashTag),
            view = node ? node.get('view') : null,
            lastView = data.currentView,
            existingItem = mainCard.child('component[routeId=' + hashTag + ']'),
            newView;

        // Kill any previously routed window
        if (lastView && lastView.isWindow) {
            lastView.destroy();
        }

        lastView = mainLayout.getActiveItem();

        if (!existingItem) {
            newView = Ext.create('yt_manager_app.view.' + (view || 'pages.BlankPage'), {
                hideMode: 'offsets',
                routeId: hashTag
            });
        }

        if (!newView || !newView.isWindow) {
            // !newView means we have an existing view, but if the newView isWindow
            // we don't add it to the card layout.
            if (existingItem) {
                // We don't have a newView, so activate the existing view.
                if (existingItem !== lastView) {
                    mainLayout.setActiveItem(existingItem);
                }
                newView = existingItem;
            }
            else {
                // newView is set (did not exist already), so add it and make it the
                // activeItem.
                Ext.suspendLayouts();
                mainLayout.setActiveItem(mainCard.add(newView));
                Ext.resumeLayouts(true);
            }
        }

        treeList.setSelection(node);
        //if (node != null && node.getParent() != null) {
        //    node.getParent().setExpanded(true);
        //}

        if (newView.isFocusable(true)) {
            newView.focus();
        }

        data.currentView = newView;
        model.set('toggle.currentViewName', node.getData().text || 'Nan');
    },

    onTreelistSelectionChange: function (tree, node) {
        var treeListContainer = this.lookupReference('tree_list_container');
        this.scrollX = treeListContainer.getScrollX();
        this.scrollY = treeListContainer.getScrollY();

        if (node) {
            this.redirectTo(node.get("routeId"));
        }
    },

    onLogoutClicked: function () {
        var me = this;
        var username = localStorage.getItem('yt_manager_app.login.username');
        Ext.Ajax.request({
            asyn: true,
            url: 'http://localhost:8080/yt-web/rest/users/logout/' + username,
            success: function (response, opts) {
                localStorage.removeItem('yt_manager_app.login.state');
                me.getView().destroy();
                Ext.create({
                    xtype: 'login'
                });
            },
            failure: function (response, opts) {
                Ext.MessageBox.alert('Error', '登出系统失败！错误码： ' + response.status + '。');
            }
        });
    },

    onRouteChange: function (id) {
        this.setCurrentView(id);
    },

    onToggleNavigationSize: function () {
        var model = this.getViewModel(),
            data = model.getData(),
            micro = data.toggle.state,
            leftPanel = this.lookupReference('left_panel'),
            rightPanel = this.lookupReference('right_panel'),
            mainText = this.lookupReference('main_text'),
            treeListContainer = this.lookupReference('tree_list_container'),
            treeList = this.lookupReference('tree_list'),
            viewTitle = this.lookupReference('view_title'),
            mainCard = this.lookupReference('main_card');

        var left_width = micro ? data.toggle.left.minLength : data.toggle.left.maxLength,
            right_width = data.toggle.availWidth - left_width,
            treeListHeight = data.toggle.availHeight - data.toggle.headerHeight;

        leftPanel.setWidth(left_width);
        treeListContainer.setWidth(left_width);
        treeListContainer.setHeight(treeListHeight);
        treeList.setWidth(left_width);
        if (micro) {
            treeList.scrollable = false;
        } else {
            treeList.scrollable = 'y';
        }
        treeList.setMicro(micro);

        rightPanel.setWidth(right_width);
        viewTitle.setWidth(right_width);
        mainCard.setWidth(right_width);

        treeListContainer.updateLayout();
        if (!micro) {
            treeListContainer.scrollTo(this.scrollX, this.scrollY);
        }

        data.toggle.state = !micro;
    },

    onItemSelected: function (sender, record) {
        Ext.Msg.confirm('Confirm', 'Are you sure?', 'onConfirm', this);
    },

    onConfirm: function (choice) {
        if (choice === 'yes') {
            //
        }
    }
});
