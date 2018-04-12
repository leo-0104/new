package com.blk.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.ocr.sdk.OCR;
import com.baidu.ocr.sdk.OnResultListener;
import com.baidu.ocr.sdk.exception.OCRError;
import com.baidu.ocr.sdk.model.AccessToken;
import com.blk.R;
import com.blk.common.CommomDialog;
import com.blk.common.HttpCallbackListener;
import com.blk.common.HttpSendUtil;
import com.blk.common.ShowAllListView;
import com.blk.medical_record.Adapter.medicalRecordDetailBaseAdapter;
import com.blk.medical_record.Adapter.person_member_info_baseAdapter;
import com.blk.medical_record.dataAnalyseAcvivity;
import com.blk.medical_record.entity.medicalRecordDetail;
import com.blk.medical_record.entity.person_member_info;
import com.blk.medical_record.medicalRecordContentActivity;
import com.blk.medical_record.medicalRecordSearchActivity;
import com.blk.medical_record.testActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus on 2017/8/9.
 */

public class medicalRecord_Fragment extends Fragment implements View.OnClickListener{
    private final int SHOW_MEDICAL_RECORD = 1;
    private View view,popupView ;
    private DrawerLayout drawerLayout;
    private ImageView icon_more,icon_add;   //点击更多的图片,选择拍照或者选择照片
    private ListView memberListView ;    //显示家庭成员的memberList
    private List<person_member_info> memberlist;   //家庭成员具体信息的列表
    private person_member_info_baseAdapter member_info_baseAdapter;  //传递给memberListView的适配器
  //  private person_member_info member_info ;   //家庭成员具体信息
    private AlertDialog.Builder alertDialog;
    private ListView medicalListView;    //显示病历详细信息的ListView
    private List<medicalRecordDetail> medicalDetailList;  //病历信息的列表
    private medicalRecordDetail medicalRecordDetail ;   //病历详细信息
    private medicalRecordDetailBaseAdapter detail_baseAdapter; //传递给medicalListView的适配器

    private PopupWindow chooseTakeSelectPop;   //选择图片或者拍照的弹出框
    private ImageView closeSelect,takePhoto,choosePhoto;  //关闭弹出框，选择图片，拍照

    private ImageView medicalRecordSearchBox;   //病历搜索框

    private final int medicalRecordSearchRequestCode = 101;   //病历搜索框请求码

    private TextView member_manage,data_analyse;       //成员管理、数据对比


    private final Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SHOW_MEDICAL_RECORD:
                    String result = (String) msg.obj;
                    onPostExecute(result);
                    break;
            }
            super.handleMessage(msg);
        }
    };

    public  static Dialog weiboDialogUtils;
    public medicalRecord_Fragment(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

      view=inflater.inflate(R.layout.medical_record,container,false);
        //初始化控件
        initView();
        //事件
       initEvent();
       // medicalDetailList.add(new medicalRecordDetail("22","2015-12-15","哈哈","111"));

        return view;
    }
    //初始化控件
    private void initView()
    {
        drawerLayout = (DrawerLayout) view.findViewById(R.id.drawerLayout);
        icon_more = (ImageView) view.findViewById(R.id.icon_more);
        icon_add = (ImageView) view.findViewById(R.id.icon_add);
        medicalListView = (ListView) view.findViewById(R.id.medical_record_detail_list);
        member_manage = (TextView) view.findViewById(R.id.member_manage);
        data_analyse = (TextView) view.findViewById(R.id.data_analyse);

        medicalDetailList = new ArrayList<medicalRecordDetail>();
        medicalRecordSearchBox = (ImageView) view.findViewById(R.id.medicalRecordSearchBox);
        AddMedicalRecordDetailList("123");   //填充病历详细信息列表
        detail_baseAdapter = new medicalRecordDetailBaseAdapter(getActivity(),medicalDetailList);//初始化适配器
        medicalListView.setAdapter(detail_baseAdapter);//将适配器传递给medicalListView，类似于填充数据
        //解决scrollview中嵌套listview只显示一个item的问题
        //ShowAllListView.setListViewHeightBasedOnChildren(medicalListView);
        memberListView = (ListView) view.findViewById(R.id.person_member_list);
        memberlist = new ArrayList<person_member_info>();
       // list.add(new person_member_info("张三"));
        AddpersonMemberInfoList();   //填充list列表
        member_info_baseAdapter = new person_member_info_baseAdapter(getActivity(),memberlist);  //初始化适配器
        memberListView.setAdapter(member_info_baseAdapter);  //将适配器传递给memberList，类似于填充数据
        //解决scrollview中嵌套listview只显示一个item的问题
      //  ShowAllListView.setListViewHeightBasedOnChildren(memberListView);
    }
//
    //根据用户的id来查询所有病历列表
    private void AddMedicalRecordDetailList(String pid) {
        String address = "http://47.95.246.177:8080/gdufs_blk_ssh/case_findPerson";
        String data = "pid=" + pid;
        HttpSendUtil.sendHttpRequest(address,data,new HttpCallbackListener() {
            @Override
            public void onFinish(String response) {
                // Toast.makeText(medical_record_detail.this, "6666", Toast.LENGTH_SHORT).show();
                Message message = new Message();
                message.what = SHOW_MEDICAL_RECORD;
                message.obj = response;
                handler.sendMessage(message);
            }

            @Override
            public void onError(Exception e) {
            }
        });

    }
    //方法：填充家庭成员信息的list列表
    private void AddpersonMemberInfoList() {
        String person_member_name = "胡先生";
       // person_member_info member_info  = new person_member_info(person_member_name);
        memberlist.add( new person_member_info(person_member_name));
        memberlist.add( new person_member_info("张三"));
        memberlist.add( new person_member_info("李四"));
//        //找到头像的目标文件
//        File file = new File("D:\\test_photo.png");
//        //建立缓冲字符串对象
//        StringBuilder sb = null;
//        //建立字节输入流对象
//        try {
//            FileInputStream fileInputStream = new FileInputStream(file);
//            //建立字节数组
//            byte[] bytes = new byte[1024];
//            int length = 0;
//            while ((length = fileInputStream.read(bytes)) != -1)
//            {
//                sb.append(new String(bytes,0,length));
//            }
//            //关闭资源
//            fileInputStream.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        //对象初始化


//        member_info.setPerson_name(person_member_name);
//        member_info.setPerson_photo();
        //将家庭成员的具体信息添加到成员列表中


    }
    protected void onPostExecute(String result) {
        if (result != null) {
            // Log.i("MainActivity","111"+result);
            try {
                JSONArray jsonArray = new JSONArray(result);

                for(int i=0;i<jsonArray.length();i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                 //   test.setText(jsonObject.toString());
                    String id = jsonObject.getString("cid");
                    String date = jsonObject.getString("vdate");
                    String hospital_name = jsonObject.getString("hospital");
                    String department = jsonObject.getString("office");
                    medicalDetailList.add(new medicalRecordDetail(id,date,hospital_name,department));
                }
                //解决scrollview中嵌套listview只显示一个item的问题
                ShowAllListView.setListViewHeightBasedOnChildren(medicalListView);

            } catch (JSONException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
    //事件
    private void initEvent()
    {
        //左边的更多图片，点击触发事件--弹出左边的抽屉
        icon_more.setOnClickListener(this);
        //选择按钮，选择图片或者拍照
        icon_add.setOnClickListener(this);
        //病历信息搜索框
        medicalRecordSearchBox.setOnClickListener(this);
        //成员管理事件
        member_manage.setOnClickListener(this);
        //数据对比
        data_analyse.setOnClickListener(this);
        //病历信息列表的点击
        medicalListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent medicalRecordContentIntent = new Intent(getActivity(),medicalRecordContentActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putInt("data",2);
//                bundle.putInt("position",position);
//                medicalRecordContentIntent.putExtras(bundle);
                Intent medicalRecordContentIntent = new Intent(getActivity(),testActivity.class);
                startActivity(medicalRecordContentIntent);
            }
        });
        //病历信息列表长按--删除病历信息
        medicalListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                TextView text_list_id = (TextView) view.findViewById(R.id.list_id);
                //弹出提示框
                new CommomDialog(getActivity(), R.style.dialog, "您确定删除此信息？", new CommomDialog.OnCloseListener() {
                    @Override
                    public void onClick(Dialog dialog, boolean confirm) {
                        if(confirm){
                            medicalDetailList.remove(position);
                            detail_baseAdapter.notifyDataSetChanged();
                            dialog.dismiss();
                            Toast.makeText(getActivity(), "删除成功", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            dialog.dismiss();
                        }

                    }
                }).setTitle("提示").show();


                return false;
            }
        });


        // 请选择您的初始化方式
        initAccessToken();

    }
//
    private void icon_addEvent()
    {
        initAddPopupWindow();
        chooseTakeSelectPop.showAsDropDown(popupView,0,0);
        backgroundAlpha(0.8f);
    }
  //初始化弹出框
    private void initAddPopupWindow()
    {
        //初始化
          popupView = LayoutInflater.from(getActivity()).inflate(R.layout.choose_take_select_popupwindow,null);
          chooseTakeSelectPop = new PopupWindow(getActivity().getWindow().getAttributes().width,getActivity().getWindow().getAttributes().height);
          closeSelect = (ImageView) popupView.findViewById(R.id.closeChoose);
          takePhoto = (ImageView) popupView.findViewById(R.id.takePhoto);
          choosePhoto = (ImageView) popupView.findViewById(R.id.choosePhoto);
          //对病历进行拍照，并识别病历信息
          takePhoto.setOnClickListener(this);
          //选择病历图片，并识别病历信息
          choosePhoto.setOnClickListener(this);
          //关闭弹出选择框
        closeSelect.setOnClickListener(this);

        chooseTakeSelectPop.setContentView(popupView);
        chooseTakeSelectPop.setClippingEnabled(false);
        chooseTakeSelectPop.setFocusable(true);
        chooseTakeSelectPop.setTouchable(true);
        chooseTakeSelectPop.setOutsideTouchable(true);
        chooseTakeSelectPop.setBackgroundDrawable(new BitmapDrawable());

    }
    private void backgroundAlpha(float f) {
        WindowManager.LayoutParams lp =getActivity().getWindow().getAttributes();
        lp.alpha = f;
        getActivity().getWindow().setAttributes(lp);
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id)
        {
            //病历信息搜索框
            case R.id.medicalRecordSearchBox:
                 Intent searchIntent = new Intent(getActivity(),medicalRecordSearchActivity.class);
                 startActivity(searchIntent);
                break;
            //选择按钮，选择图片或者拍照
            case R.id.icon_add:
                icon_addEvent();
                break;
            //左边的更多图片，点击触发事件--弹出左边的抽屉
            case R.id.icon_more:
                drawerLayout.openDrawer(Gravity.LEFT);
                break;
            //对病历进行拍照，并识别病历信息
            case R.id.takePhoto:
                Intent takePhotointent = new Intent(getActivity(),medicalRecordContentActivity.class);
                backgroundAlpha(1.0f);
                chooseTakeSelectPop.dismiss();
                chooseTakeSelectPop = null;
                   /* 通过Bundle对象存储需要传递的数据 */
                Bundle bundleTake=new Bundle();
                bundleTake.putInt("data",1);
                /*把bundle对象assign给Intent*/
                takePhotointent.putExtras(bundleTake);
                startActivity(takePhotointent);
                break;
            //选择病历图片，并识别病历信息
            case R.id.choosePhoto:
                Intent choosePhotointent = new Intent(getActivity(),medicalRecordContentActivity.class);
                backgroundAlpha(1.0f);
                chooseTakeSelectPop.dismiss();
                chooseTakeSelectPop = null;
                   /* 通过Bundle对象存储需要传递的数据 */
                Bundle bundleChoose=new Bundle();
                bundleChoose.putInt("data",0);
                /*把bundle对象assign给Intent*/
                choosePhotointent.putExtras(bundleChoose);
                startActivity(choosePhotointent);
                break;
            //关闭弹出选择框
            case R.id.closeChoose:
                backgroundAlpha(1.0f);
                chooseTakeSelectPop.dismiss();
                chooseTakeSelectPop = null;
                break;
            //家庭成员管理
            case R.id.member_manage:
                break;
            //数据对比
            case R.id.data_analyse:
                Intent dataAnalyseIntent = new Intent(getActivity(),dataAnalyseAcvivity.class);
                startActivity(dataAnalyseIntent);
                break;

        }
    }



    private void initAccessToken() {

        OCR.getInstance().initAccessToken(new OnResultListener<AccessToken>() {
            @Override
            public void onResult(AccessToken accessToken) {
                String token = accessToken.getAccessToken();
            }

            @Override
            public void onError(OCRError error) {
                error.printStackTrace();
                alertText("licence方式获取token失败", error.getMessage());
            }
        }, getActivity());
    }

    private void alertText(String title, String message) {
        boolean isNeedLoop = false;
        if (Looper.myLooper() == null) {
            Looper.prepare();
            isNeedLoop = true;
        }
        alertDialog.setTitle(title)
                .setMessage(message)
                .setPositiveButton("确定", null)
                .show();
        if (isNeedLoop) {
            Looper.loop();
        }
    }

    //动态处理权限问题

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[]  permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            initAccessToken();
        } else {
            Toast.makeText(getActivity(), "需要android.permission.READ_PHONE_STATE", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // 释放内存资源
        OCR.getInstance().release();
    }
}

