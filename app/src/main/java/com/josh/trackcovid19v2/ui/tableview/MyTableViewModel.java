package com.josh.trackcovid19v2.ui.tableview;

import android.view.Gravity;

import com.josh.trackcovid19v2.data.database.entity.States;
import com.josh.trackcovid19v2.ui.tableview.model.CellModel;
import com.josh.trackcovid19v2.ui.tableview.model.ColumnHeaderModel;
import com.josh.trackcovid19v2.ui.tableview.model.RowHeaderModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by evrencoskun on 4.02.2018.
 */

public class MyTableViewModel {
    // View Types
    public static final int GENDER_TYPE = 1;
    public static final int MONEY_TYPE = 2;

    private List<ColumnHeaderModel> mColumnHeaderModelList;
    private List<RowHeaderModel> mRowHeaderModelList;
    private List<List<CellModel>> mCellModelList;

    public int getCellItemViewType(int column) {

        switch (column) {
            /*
            case 5:
                // 5. column header is gender.
                return GENDER_TYPE;
            case 8:
                // 8. column header is Salary.
                return MONEY_TYPE;

             */
            default:
                return 0;
        }
    }

     /*
       - Each of Column Header -
            "Id"
            "Name"
            "Nickname"
            "Email"
            "Birthday"
            "Gender"
            "Age"
            "Job"
            "Salary"
            "CreatedAt"
            "UpdatedAt"
            "Address"
            "Zip Code"
            "Phone"
            "Fax"
     */

    public int getColumnTextAlign(int column) {
        switch (column) {
            // Id
            case 0:
                return Gravity.CENTER;
            // Name
            case 1:
                return Gravity.LEFT;
            // Nickname
            case 2:
                return Gravity.LEFT;
            // Email
            case 3:
                return Gravity.LEFT;
            // BirthDay
            case 4:
                return Gravity.CENTER;
            // Gender (Sex)
            case 5:
                return Gravity.CENTER;
            // Age
            case 6:
                return Gravity.CENTER;
            // Job
            case 7:
                return Gravity.LEFT;
            // Salary
            case 8:
                return Gravity.CENTER;
            // CreatedAt
            case 9:
                return Gravity.CENTER;
            // UpdatedAt
            case 10:
                return Gravity.CENTER;
            // Address
            case 11:
                return Gravity.LEFT;
            // Zip Code
            case 12:
                return Gravity.RIGHT;
            // Phone
            case 13:
                return Gravity.RIGHT;
            // Fax
            case 14:
                return Gravity.RIGHT;
            default:
                return Gravity.CENTER;
        }

    }

    private List<ColumnHeaderModel> createColumnHeaderModelList() {
        List<ColumnHeaderModel> list = new ArrayList<>();

        // Create Column Headers
        list.add(new ColumnHeaderModel("State"));
        list.add(new ColumnHeaderModel("Cases"));
        list.add(new ColumnHeaderModel("Today's Cases"));
        list.add(new ColumnHeaderModel("Deaths"));
        list.add(new ColumnHeaderModel("Today's Deaths"));
        list.add(new ColumnHeaderModel("Active Cases"));


        return list;
    }

    private List<List<CellModel>> createCellModelList(List<States> stateList) {
        List<List<CellModel>> lists = new ArrayList<>();

        // Creating cell model list from User list for Cell Items
        // In this example, State list is populated from web service

        for (int i = 0; i < stateList.size(); i++) {
            States state_data = stateList.get(i);

            List<CellModel> list = new ArrayList<>();

            // The order should be same with column header list;
            list.add(new CellModel("1-" + i, state_data.state));          // "Id"
            list.add(new CellModel("2-" + i, state_data.cases));        // "Name"
            list.add(new CellModel("3-" + i, state_data.todayCases));    // "Nickname"
            list.add(new CellModel("4-" + i, state_data.deaths));       // "Email"
            list.add(new CellModel("5-" + i, state_data.todayDeaths));   // "BirthDay"
            list.add(new CellModel("6-" + i, state_data.active));

            // Add
            lists.add(list);
        }

        return lists;
    }

    private List<RowHeaderModel> createRowHeaderList(int size) {
        List<RowHeaderModel> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            // In this example, Row headers just shows the index of the TableView List.
            list.add(new RowHeaderModel(String.valueOf(i + 1)));
        }
        return list;
    }


    public List<ColumnHeaderModel> getColumHeaderModeList() {
        return mColumnHeaderModelList;
    }

    public List<RowHeaderModel> getRowHeaderModelList() {
        return mRowHeaderModelList;
    }

    public List<List<CellModel>> getCellModelList() {
        return mCellModelList;
    }


    public void generateListForTableView(List<States> states) {
        mColumnHeaderModelList = createColumnHeaderModelList();
        mCellModelList = createCellModelList(states);
        mRowHeaderModelList = createRowHeaderList(states.size());
    }

}



