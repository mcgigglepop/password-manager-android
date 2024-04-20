package adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mcgigglepop.myprofilebox.R;

import java.util.ArrayList;
import java.util.List;

import models.Account;

public class AccountAdapter extends ArrayAdapter<Account>
{

    public AccountAdapter(Context context, int resource, List<Account> shapeList)
    {
        super(context,resource,shapeList);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        Account account = getItem(position);

        if(convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_account, parent, false);
        }
        TextView tv = (TextView) convertView.findViewById(R.id.nameTextView);

        tv.setText(account.getTitle());


        return convertView;
    }
}