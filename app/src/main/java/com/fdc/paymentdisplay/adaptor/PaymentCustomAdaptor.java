package com.fdc.paymentdisplay.adaptor;

import com.fdc.paymentdisplay.PaymentDetailsInterface;
import com.fdc.paymentdisplay.R;
import com.fdc.paymentdisplay.constant.Constants;
import com.fdc.paymentdisplay.modal.OrderModal;
import com.fdc.paymentdisplay.modal.PaymentDate;
import com.fdc.paymentdisplay.modal.PaymentRowDetails;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by mgupta4 on 8/25/2016.
 */
public class PaymentCustomAdaptor extends BaseAdapter {

    private final int LIST_ROW_TYPE = 2;
    private List<PaymentDetailsInterface> mPaymentDetailsInterfaceList;
    private LayoutInflater inflater;

    public PaymentCustomAdaptor(Context context, List<PaymentDetailsInterface> paymentDetailsInterfaces) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mPaymentDetailsInterfaceList = paymentDetailsInterfaces ;
    }

    @Override
    public int getItemViewType(int position) {

        int itemType = Constants.HEADER_ROW;

        if (mPaymentDetailsInterfaceList.get(position) instanceof PaymentRowDetails) {
            itemType = Constants.DETAIL_ROW;
        }

        return itemType;

    }

    @Override
    public int getCount() {
        return mPaymentDetailsInterfaceList.size();
    }

    @Override
    public PaymentDetailsInterface getItem(int position) {
        return mPaymentDetailsInterfaceList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return LIST_ROW_TYPE;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = null;
        final PaymentDetailsInterface paymentDetailsInterface = mPaymentDetailsInterfaceList.get(position);

        switch (getItemViewType(position)) {
            case Constants.HEADER_ROW:

                if (convertView == null) {
                    view = inflater.inflate(R.layout.payment_list_header, parent, false);
                    ViewHolder viewHolder = setupHeaderViewHolder(view);
                    view.setTag(viewHolder);
                } else {
                    view = convertView;
                }

                ViewHolder headerHolder = (ViewHolder) view.getTag();
                String date = ((PaymentDate) paymentDetailsInterface).getDate();
                headerHolder.paymentDate.setText(date);
                break;

            case Constants.DETAIL_ROW:

                if (convertView == null) {
                    view = inflater.inflate(R.layout.payment_list_row, parent, false);
                    ViewHolder viewHolder = setupDetailViewHolder(view);
                    view.setTag(viewHolder);
                } else {
                    view = convertView;
                }

                ViewHolder detailRowHolder = (ViewHolder) view.getTag();
                String paymentType = ((PaymentRowDetails) paymentDetailsInterface).getPaymentType();
                String paymentAmount = ((PaymentRowDetails) paymentDetailsInterface).getPaymentAmount();
                detailRowHolder.paymentType.setText(paymentType);
                detailRowHolder.paymentAmount.setText(paymentAmount);

                break;

        }

        return view;

    }


    static class ViewHolder {

        private TextView paymentDate;
        private TextView paymentType;
        private TextView paymentAmount;


    }


    private ViewHolder setupHeaderViewHolder(final View view) {
        final ViewHolder viewHolder = new ViewHolder();
        viewHolder.paymentDate = (TextView) view
                .findViewById(R.id.dateheader);
        return viewHolder;
    }

    private ViewHolder setupDetailViewHolder(final View view) {
        final ViewHolder viewHolder = new ViewHolder();
        viewHolder.paymentType = (TextView) view
                .findViewById(R.id.paymenttype);
        viewHolder.paymentAmount = (TextView) view
                .findViewById(R.id.paymentamount);

        return viewHolder;
    }
}
