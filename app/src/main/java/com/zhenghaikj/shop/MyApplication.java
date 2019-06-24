package com.zhenghaikj.shop;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;

import com.blankj.utilcode.util.Utils;
import com.qiyukf.unicorn.api.OnMessageItemClickListener;
import com.qiyukf.unicorn.api.StatusBarNotificationConfig;
import com.qiyukf.unicorn.api.Unicorn;
import com.qiyukf.unicorn.api.YSFOptions;
import com.qiyukf.unicorn.api.pop.OnShopEventListener;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.tencent.android.tpush.XGIOperateCallback;
import com.tencent.android.tpush.XGPushConfig;
import com.tencent.android.tpush.XGPushManager;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;
import com.tencent.bugly.beta.UpgradeInfo;
import com.tencent.bugly.beta.download.DownloadListener;
import com.tencent.bugly.beta.download.DownloadTask;
import com.tencent.bugly.beta.upgrade.UpgradeListener;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;
import com.zhenghaikj.shop.activity.GoodsDetailActivity;
import com.zhenghaikj.shop.activity.MessageActivity2;
import com.zhenghaikj.shop.activity.StoreDetailActivity;
import com.zhenghaikj.shop.utils.imageutil.GlideImageLoader;

import org.greenrobot.eventbus.EventBus;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


/**
 * 描述：
 * 作者：HMY
 * 时间：2016/5/13
 */
public class MyApplication extends MultiDexApplication {
    static {//static 代码段可以防止内存泄露
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
               /* MaterialHeader header=new MaterialHeader(context);
                header.setPrimaryColors(Color.parseColor("#00000000"));
                header.setShowBezierWave(true);
                layout.setEnableHeaderTranslationContent(false);
                return header;*/
                //layout.setPrimaryColorsId(R.color.white, android.R.color.black);//全局设置主题颜色


                return new ClassicsHeader(context);//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header

                //指定为经典Header，默认是 贝塞尔雷达Header
//                return new ClassicsHeader(context).setSpinnerStyle(SpinnerStyle.Translate);//指定为经典Header，默认是 贝塞尔雷达Header
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                return new ClassicsFooter(context).setSpinnerStyle(SpinnerStyle.Translate).setFinishDuration(0);
            }
        });
    }


    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
        // appKey 可以在七鱼管理系统->setting->APP接入 页面找到
        Unicorn.init(this, "b081b3984c11db4d519a5036a5c2bb98 ", options(), new GlideImageLoader(getApplicationContext()));
        XGPushConfig.enableDebug(this,true);
        XGPushConfig.enableOtherPush(getApplicationContext(), true);
//        XGPushConfig.setHuaweiDebug(true);
//        XGPushConfig.setMiPushAppId(getApplicationContext(), "2882303761517940129");
//        XGPushConfig.setMiPushAppKey(getApplicationContext(), "5461794037129");
        XGPushConfig.setMzPushAppId(this, "d09a727943455");
        XGPushConfig.setMzPushAppKey(this, "71940f07a1a5bb36e89705b28f35b20f");
        XGPushManager.registerPush(this, new XGIOperateCallback() {
            @Override
            public void onSuccess(Object data, int flag) {
//token在设备卸载重装的时候有可能会变
                Log.d("TPush", "注册成功，设备token为：" + data);
            }
            @Override
            public void onFail(Object data, int errCode, String msg) {
                Log.d("TPush", "注册失败，错误码：" + errCode + ",错误信息：" + msg);
            }
        });
        XGPushManager.bindAccount(getApplicationContext(), "XINGE");
        XGPushManager.setTag(this,"XINGE");


        /*Context context = getApplicationContext();
// 获取当前包名
        String packageName = context.getPackageName();
// 获取当前进程名
        String processName = getProcessName(android.os.Process.myPid());
// 设置是否为上报进程
        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(context);
        strategy.setUploadProcess(processName == null || processName.equals(packageName));
// 初始化Bugly
        CrashReport.initCrashReport(context, "52f54c7015", true, strategy);*/

        // 这里实现SDK初始化，appId替换成你的在Bugly平台申请的appId
        // 调试时，将第三个参数改为true
        Beta.enableNotification = true;
        Beta.upgradeListener=new UpgradeListener() {
            @Override
            public void onUpgrade(int ret, UpgradeInfo upgradeInfo, boolean b, boolean b1) {
                if (upgradeInfo != null) {
                    Beta.showUpgradeDialog(
                            upgradeInfo.title,
                            upgradeInfo.upgradeType,
                            upgradeInfo.newFeature,
                            upgradeInfo.publishTime,
                            upgradeInfo.versionCode,
                            upgradeInfo.versionCode,
                            upgradeInfo.versionName,
                            upgradeInfo.apkUrl,
                            upgradeInfo.fileSize,
                            upgradeInfo.apkMd5,
                            upgradeInfo.imageUrl,
                            0,
                            listener,
                            null,
                            null,
                            b
                    );
                } else {
                    EventBus.getDefault().post("update");
                }
            }
        };
        Bugly.init(this, "52f54c7015", true);

        UMConfigure.init(this,"5ce64a753fc195674900125f"
                ,"umeng", UMConfigure.DEVICE_TYPE_PHONE,"");//58edcfeb310c93091c000be2 5965ee00734be40b580001a0
        //微信
        PlatformConfig.setWeixin("wx92928bf751e1628e", "81e1e0c14cade7740f765240b9838f40");
        //新浪
        PlatformConfig.setSinaWeibo("2520420227", "e76b6df1fd9b46b41b7ea70008cde46a","http://sns.whalecloud.com");
        //QQ
        PlatformConfig.setQQZone("1109159306", "h1ECHyxVEiZ18ews");
    }

    /**
     * 获取进程号对应的进程名
     *
     * @param pid 进程号
     * @return 进程名
     */
    private static String getProcessName(int pid) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("/proc/" + pid + "/cmdline"));
            String processName = reader.readLine();
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim();
            }
            return processName;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }
    // 如果返回值为null，则全部使用默认参数。
    private YSFOptions options() {
        YSFOptions options = new YSFOptions();
        options.statusBarNotificationConfig = new StatusBarNotificationConfig();
        options.onShopEventListener = new OnShopEventListener() {
            @Override
            public boolean onShopEntranceClick(Context context, String shopId) {
                // 点击商家入口响应
                Intent intent = new Intent(context, StoreDetailActivity.class);
                intent.putExtra("VShopId", shopId);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                return true;
            }

            @Override
            public boolean onSessionListEntranceClick(Context context) {
                // 点击会话列表入口响应，以下为示例代码
//                SessionListActivity.start(context);
                Intent intent = new Intent(context, MessageActivity2.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                return true;
            }
        };
        OnMessageItemClickListener messageItemClickListener = new OnMessageItemClickListener() {
            // 响应 url 点击事件
            public void onURLClicked(Context context, String url) {
                // 打开内置浏览器等动作
                Intent intent = new Intent(context, GoodsDetailActivity.class);
                intent.putExtra("id", url.substring(url.lastIndexOf("/")+1));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        };
        options.onMessageItemClickListener = messageItemClickListener;
        return options;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        // you must install multiDex whatever tinker is installed!
        MultiDex.install(base);
        // 安装tinker
        Beta.installTinker();
    }
    DownloadListener listener=new DownloadListener() {
        @Override
        public void onReceive(DownloadTask downloadTask) {

        }

        @Override
        public void onCompleted(DownloadTask downloadTask) {

        }

        @Override
        public void onFailed(DownloadTask downloadTask, int i, String s) {

        }
    };
}
