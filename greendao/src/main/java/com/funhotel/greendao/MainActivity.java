package com.funhotel.greendao;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.funhotel.greendao.entity.User;
import com.funhotel.greendao.gen.DaoMaster;
import com.funhotel.greendao.gen.DaoSession;
import com.funhotel.greendao.gen.UserDao;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mAdd,mDelete,mUpdate,mFind;
    private TextView mContext;
    private User mUser;
    private UserDao mUserDao;
    private long id=110;
    private int i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initEvent();
        DaoMaster.DevOpenHelper devOpenHelper=new DaoMaster.DevOpenHelper(this,"huanlvdb",null);
        DaoMaster daoMaster=new DaoMaster(devOpenHelper.getWritableDatabase());
        DaoSession daoSession=daoMaster.newSession();


        mUserDao =daoSession.getUserDao();
    }

    private void initEvent() {
        mAdd.setOnClickListener(this);
        mDelete.setOnClickListener(this);
        mUpdate.setOnClickListener(this);
        mFind.setOnClickListener(this);
    }

    private void initView() {

        mContext = (TextView) findViewById(R.id.textView);
        mAdd = (Button) findViewById(R.id.button);
        mDelete = (Button) findViewById(R.id.button2);
        mUpdate = (Button) findViewById(R.id.button3);
        mFind = (Button) findViewById(R.id.button4);

        mContext.setText(Build.MODEL+">>>"+Build.DEVICE+">>>"+Build.ID+">>>"+Build.PRODUCT);
    }




    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                addDate();
                break;
            case R.id.button2:
                deleteDate();
                break;
            case R.id.button3:
                updateDate();
                break;
            case R.id.button4:
                findDate();
                break;
        }
    }

    /**
     * 增加数据
     */
    private void addDate() {
        try {
            mUser = new User(id,"anyzxzxzxe3"+id);
            id++;
            Log.e("uiu",""+id);
            mUserDao.insert(mUser);//添加一个
            mContext.setText(mUser.getName());
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    /**
     * 删除数据
     */
    private void deleteDate() {
        deleteUserById(2);
    }

    /**
     * 根据主键删除User
     *
     * @param id User的主键Id
     */
    public void deleteUserById(long id) {
        if (id<mUserDao.count()) {
            mUserDao.deleteByKey(id);
            id++;
        }

    }

    /**
     * 更改数据
     */
    private void updateDate() {
        id=0;
        mUser = new User(id,"anye0803"+i);
        mUserDao.update(mUser);
        i++;
    }

    /**
     * 查找数据
     */
    private void findDate() {
        List<User> users = mUserDao.loadAll();
        String userName = "";
        for (int i = 0; i < users.size(); i++) {
            userName += users.get(i).getName()+",";
        }
        mContext.setText("查询全部数据==>"+userName);
    }

}
