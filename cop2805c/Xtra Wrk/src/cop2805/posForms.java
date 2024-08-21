package cop2805;

//  POSITIONING TEXT FIELDS AND LABELS
//  easier to manipulate


class posForms
{
    //  (grid system: NONE):    L,   T,  W,  H, Tab#

    //  Line 1: ID
    static int[] aLblIDTag = { 50, 100, 20, 20, 100};
    static int[] aLblIDVal = { 50, 125, 35, 25,   0};

    //  Name
    static int[] aLblTitle = { 50, 100,  30, 20, 100};
    static int[] aTxtTitle = { 50, 125,  55, 25,   1};
    static int[] aLblFirst = {115, 100, 80,  20, 102};
    static int[] aTxtFirst = {115, 125,  85, 25,   2};
    static int[] aLblMiddl = {210, 100,  20, 20, 103};
    static int[] aTxtMiddl = {210, 125,  20, 25,   3};
    static int[] aLblLastN = {240, 100, 100, 20, 104};
    static int[] aTxtLastN = {240, 125,  85, 25,   4};


    //  Photo
    static int[] aBtnPhoto = {350, 100,  90, 25,   5};
    static int[] possPhoto = {350, 125,  90, 90};



    //  Line 2: Phone
    //                          X,   Y,  Wd, Ht, Tab#
    static int[] aLblPhon1 = { 50, 170,  90, 20, 106};
    static int[] aTxtPhon1 = { 50, 190, 120, 25,   6};
    static int[] aLblPhon2 = {180, 170,  90, 20, 107};
    static int[] aTxtPhon2 = {180, 190, 120, 25,   7};


    //  Line 3: Address
    static int[] aLblAddrs = { 50, 220,  70, 20, 108};
    static int[] aTxtAddrs = { 50, 240, 170, 25,   8};
    static int[] aLablCity = {235, 220,  70, 20, 109};
    static int[] aTextCity = {235, 240, 100, 25,   9};
    static int[] aLblState = {345, 220,  45, 20, 110};
    static int[] aTxtState = {345, 240, 25, 25,   10};
    static int[] aLblZipCd = {390, 220,  70, 20, 111};
    static int[] aTxtZipCd = {385, 240,  45, 25,  11};


    //  Line 4: Email
    //                   topLeft_X, topLeft_Y,  Wd, Ht
    static int[] lablEmail = { 50, 275,  70, 20, 112};
    static int[] textEmail = { 50, 295, 210, 25,  12};

    //  DOB
    static int[] lDateOBth = {280, 275, 120, 20, 113};
    static int[] spinerDay = {277, 295,  50, 25,  13};
    static int[] spinerMon = {335, 295,  40, 25, 114};
    static int[] text_Year = {385, 295,  45, 25,  14};


    //  Line 5: Gender
    static int[] radioMale = { 50, 340,  60, 25,  15};
    static int[] radFemale = { 50, 360,  90, 25,  15};

    // Marital Status
    static int[] radSingle = {170, 340,  80, 20,  16};
    static int[] radMaried = {250, 340,  90, 20,  16};
    static int[] radSepard = {340, 340, 120, 20,  16};

    static int[] radWidowd = {170, 360,  80, 20,  16};
    static int[] radDivord = {250, 360,  90, 20,  16};
    static int[] radCmonLw = {340, 360, 140, 20,  16};


    //  Line 6: Password
    static int[] lblPasswd = { 50, 395, 100, 20, 117};
    static int[] pswPasswd = { 50, 420, 125, 25,  17};
    static int[] lblConfir = {195, 395, 100, 20, 118};
    static int[] pswConfir = {195, 420, 125, 25,  18};


    //  Notification
    static int[] lblNotify = {373, 410, 120, 15, 119};
    static int[] radNotify = {340, 400, 120, 60,  19};


    //  Line 7: Notes
    static int[] aLblNotes = { 50, 460, 150, 20, 120};
    static int[] aTxtNotes = { 50, 485, 385, 70,  20};

}
