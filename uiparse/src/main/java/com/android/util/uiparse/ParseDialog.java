package com.android.util.uiparse;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by xuzhb on 2020/12/13
 */
public class ParseDialog extends DialogFragment {

    public static ParseDialog newInstance() {
        return new ParseDialog();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_TITLE, R.style.DialogStyle);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_ui_parse, container, false);
        initView(view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        initParams();
    }

    private void initView(View view) {
        if (getActivity() != null) {
            ActivityStructure structure = ParseUtil.getActivityStructure(getActivity());
            ((TextView) view.findViewById(R.id.package_name_tv)).setText(structure.getPackageName());
            ((TextView) view.findViewById(R.id.activity_name_tv)).setText(structure.getActivityName());
            ((TextView) view.findViewById(R.id.activity_path_tv)).setText(structure.getActivityPath());
            if (structure.getFragmentList().size() > 0) {
                view.findViewById(R.id.fragment_ll).setVisibility(View.VISIBLE);
                ((RecyclerView) view.findViewById(R.id.fragment_rv)).setAdapter(new FragmentAdapter(getActivity(), structure.getFragmentList()));
                if (structure.getTopFragment() != null) {
                    ((TextView) view.findViewById(R.id.fragment_tv)).setText(structure.getTopFragment().getName());
                }
            } else {
                view.findViewById(R.id.fragment_ll).setVisibility(View.GONE);
            }
        }
    }

    private void initParams() {
        Window window = getDialog().getWindow();
        if (window != null && getContext() != null) {
            WindowManager.LayoutParams params = window.getAttributes();
            params.dimAmount = 0.1f;
            params.gravity = Gravity.CENTER;
            params.width = (int) Util.dp2px(getContext(), 300);
            params.height = WindowManager.LayoutParams.WRAP_CONTENT;
            window.setAttributes(params);
        }
        setCancelable(true);
    }

    public void show(FragmentManager manager) {
        super.show(manager, ParseDialog.class.getName());
    }

}
