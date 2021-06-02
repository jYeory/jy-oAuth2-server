package com.jyeory.sso.common;

public class Constant {
    
    public static final String  DEV_MODE_DEFAULT            = "OP";            // 실운영 서버
    
    // 카테고리 분류
    public static final int  CATEGORY_CAR                = 10;              // 자동차 카테고리
    public static final int  CATEGORY_TRUCK              = 20;              // 트럭 카테고리
    public static final int  CATEGORY_BUS                = 30;              // 버스 카테고리
    public static final int  CATEGORY_EQUIP              = 40;              // 중장비 카테고리
    public static final int  CATEGORY_PART               = 50;              // 부품 카테고리
    public static final int  CATEGORY_MACHINERY          = 60;              // 기계류 카테고리
    
    // 멤버십 
    public static final String  MEMBERSHIP_POWER             = "C010";     // 구 파워멤버
    public static final String  MEMBERSHIP_EXPERT            = "C050";     // 구 엑스퍼트
    public static final String  MEMBERSHIP_FREIGHT           = "C090";     // 운임사
    public static final String  MEMBERSHIP_POWER_NEW         = "C210";     // 2014 파워멤버
    public static final String  MEMBERSHIP_EXPERT_NEW        = "C220";     // 2014 엑스퍼트
    public static final String  MEMBERSHIP_DIRECTORY_BASIC   = "C230";     // 디렉토리 베이직
    public static final String  MEMBERSHIP_DIRECTORY_DELUXE  = "C240";     // 디렉토리 디럭스
    public static final String  MEMBERSHIP_DIRECTORY_PREMIUM = "C250";     // 디렉토리 프리미엄
    public static final String  MEMBERSHIP_SUPER_SELLER      = "C260";     // 슈퍼 셀러
    public static final String  MEMBERSHIP_POWER_NEW10       = "C270";     // 2014 파워멤버10
    
    
    // 멤버십 존재 여부 및 멤버십 관련 권한 설정 (WINI_USERMASTER  :  N_MEMBERSHIP_RIGHT)
    public static final int     FLAG_FREIGHT                            = 0x1;          // 운임사  존재 여부
    public static final int     FLAG_POWER_NEW                          = 0x2;          // 2014 파워멤버 존재 여부
    public static final int     FLAG_EXPERT_NEW                         = 0x4;          // 2014 엑스퍼트 존재 여부
    public static final int     FLAG_DIRECTORY_BASIC                    = 0x8;          // 디렉토리 베이직 존재 여부
    public static final int     FLAG_DIRECTORY_DELUXE                   = 0x10;         // 디렉토리 디럭스 존재 여부
    public static final int     FLAG_DIRECTORY_PREMIUM                  = 0x20;         // 디렉토리 프리미엄 존재 여부
    public static final int     FLAG_SUPER_SELLER                       = 0x40;         // 슈퍼셀러 존재여부
    public static final int     FLAG_POWER_NEW10                        = 0x80;         // 2014 파워멤버 10 존재 여부
    public static final int     FLAG_CSV_UPLOAD                         = 0x100;        // JAPAN CSV파일 업로드 권한여부 256
    public static final int     FLAG_CSV_UPLOAD_CAR                     = 0x200;        // Common CSV CAR 업로드 권한 여부 512
    public static final int     FLAG_PRIME_MEMBER                       = 0x400;        // 프라임 멤버 존재 여부              1024
    public static final int     FLAG_HOTMARK                            = 0x800;        // 노클레임(GOOD QUALITY) 체크  2048
    public static final int     FLAG_RELIABLE_CHECK                     = 0x1000;       // 컨디션 리포트 Reliable 뱃지 체크 4096
    public static final int     FLAG_EXCLUSIVE_MEMBER                   = 0x2000;       // 전용 멤버 체크                             8192
    public static final int     FLAG_GUARANTEE                          = 0x4000;       // 전용 멤버 개런티 체크(프라임 플러스)    16384
    public static final int     FLAG_SELF_BOOKING                       = 0x8000;       // 셀러 - 셀프 부킹 권한                        32768
    
    // 디렉토리 관련
    // 디렉토리 카테고리 
    public static final String  DIR_CATEGORY_1ST_CODE       = "PC0229";           
    public static final String  DIR_CATEGORY_2ST_CODE       = "PC0230";           
    public static final String  DIR_CATEGORY_3ST_CODE       = "PC0231";           
    
    public static final String  DIR_STATE_APPLY             = "101";        // 신청
    public static final String  DIR_STATE_WAIT              = "102";        // 신청 대기
    public static final String  DIR_STATE_COMPLETE          = "103";        // 신청 완료
    public static final String  DIR_STATE_REJECT            = "104";        // 거절
    
    
    public static final String  EMAIL_SERVICE_TYPE_POWER    = "C010";        // 파워 멤버 신청
    public static final String  EMAIL_SERVICE_TYPE_PARTS    = "C040";        // 부품, 대량 주문
    public static final String  EMAIL_SERVICE_TYPE_SYNC     = "C080";        // 매물 연동
    public static final String  EMAIL_SERVICE_TYPE_FREE_ITEM= "C100";        // 무료 매물 등록 문의
    public static final String  EMAIL_SERVICE_TYPE_DIR      = "C110";        // 디렉토리 등록
     
    
    
    // 매물 리스팅 점수 플래그 모든 매물(N_FLAG_SORT)
    public static final int     FLAG_EDIT_CHECK_BIS             = 0x1;        // 수정시 BIS 체크 했을 때 1
    public static final int     FLAG_EDIT_UNCHECK_BIS           = 0x2;        // 수정시 BIS 체크 해제 했을 때 2
    public static final int     FLAG_EDIT_CHECK_CR              = 0x4;        // 수정시 CR 체크 했을 떄 4
    public static final int     FLAG_EDIT_UNCHECK_CR            = 0x8;        // 수정시 CR 체크 해제 했을 때 8
    public static final int     FLAG_EDIT_ADD_VIDEO             = 0x10;       // 수정시 VIDEO 추가 했을 때 16
    public static final int     FLAG_EDIT_DEL_VIDEO             = 0x20;       // 수정시 VIDEO 삭제 했을 때 32
    public static final int     FLAG_EXPIRED_DATE_SOLD_OUT      = 0x40;       // 매물 기간 완료로 판매완료 처리 됬을 때 64
    public static final int     FLAG_SORT_PRIME_MEMBER          = 0x80;       // 프라임 멤버 매물로 등록되어 +400 되었을 때 128
    
    
    
    //booking FLAG TYPE 
    public static final int  FLAG_TYPE_QUOTATION            = 10;        // QUOTATION TYPE
    public static final int  FLAG_TYPE_CNEE                 = 20;        // CNEE TYPE
    public static final int  FLAG_TYPE_PAYMENT              = 30;        // PAYMENT RECEIPT TYPE
    public static final int  FLAG_TYPE_CONFIRMATION         = 40;        // CONFIRMATION RECEIPT TYPE
    public static final int  FLAG_TYPE_SHIPPING             = 50;        // SHIPPIN TYPE
    public static final int  FLAG_TYPE_DELIVERY             = 60;        // DELIVERY TYPE
    public static final int  FLAG_TYPE_PI                   = 70;        // PI TYPE
    public static final int  FLAG_TYPE_CI                   = 80;        // CI TYPE
    public static final int  FLAG_TYPE_BL                   = 90;        // BL TYPE
    public static final int  FLAG_TYPE_TRACKING             = 100;        // TARCKIN TYPE

    
    //booking documnets confirm  에 따른 상태값
    public static final int     FLAG_CHECK_QUOTATION                = 0x00000001;        // QUOTATION 변경시
    public static final int     FLAG_CHECK_CNEE                     = 0x00000002;        // CNEE 변경시
    public static final int     FLAG_CHECK_PAYMENT_RECEIPT          = 0x00000004;        // 송금 영수증 업로드시  
    public static final int     FLAG_CHECK_CONFIRMATION_RECEIPT     = 0x00000008;        // 입금 확인 영수증 업로드시 
    public static final int     FLAG_CHECK_SHIPPING                 = 0x00000010;        // 운임정보 입력시
    public static final int     FLAG_CHECK_DELIVERY                 = 0x00000020;        // Delivery 정보 변경되었을 때 
    public static final int     FLAG_CHECK_PI                       = 0x00000040;        // PI 변경사항 있을 경우 
    public static final int     FLAG_CHECK_CI                       = 0x00000080;        // CI 변경사항 있을 경우
    public static final int     FLAG_CHECK_BL                       = 0x00000100;        // BL 변경사항 있을 경우 
    public static final int     FLAG_CHECK_TRACKING                 = 0x00000200;        // Tracking 변경사항 있을 경우
    
    
    // Super Seller Banner Page 권한 카테고리 
    public static final int     SUPER_PAGE_RIGHT_CAR                = 0x1;          // 슈퍼 셀러 베너 페이지 권한 자동차 1
    public static final int     SUPER_PAGE_RIGHT_SALVAGE            = 0x2;          // 슈퍼 셀러 베너 페이지 권한 폐차 2
    public static final int     SUPER_PAGE_RIGHT_TRUCK              = 0x4;          // 슈퍼 셀러 베너 페이지 권한 트럭 4
    public static final int     SUPER_PAGE_RIGHT_BUS                = 0x8;          // 슈퍼 셀러 베너 페이지 권한 버스 8
    public static final int     SUPER_PAGE_RIGHT_EQUIP              = 0x10;         // 슈퍼 셀러 베너 페이지 권한 중장비 16 
    public static final int     SUPER_PAGE_RIGHT_EQUIP_PART         = 0x20;         // 슈퍼 셀러 베너 페이지 권한 중장비 부품 32 
    public static final int     SUPER_PAGE_RIGHT_PART               = 0x40;         // 슈퍼 셀러 베너 페이지 권한 부품 64
    public static final int     SUPER_PAGE_RIGHT_MACHINERY          = 0x80;         // 슈퍼 셀러 베너 페이지 권한 기계류 128
    
    public static final int     SUPER_PHOTO_RIGHT_CAR                = 0x00000100;          // 슈퍼 셀러 베너 사진 권한 자동차 256
    public static final int     SUPER_PHOTO_RIGHT_SALVAGE            = 0x00000200;          // 슈퍼 셀러 베너 사진 권한 폐차 512
    public static final int     SUPER_PHOTO_RIGHT_TRUCK              = 0x00000400;          // 슈퍼 셀러 베너 사진 권한 트럭 1024
    public static final int     SUPER_PHOTO_RIGHT_BUS                = 0x00000800;          // 슈퍼 셀러 베너 사진 권한 버스 2048
    public static final int     SUPER_PHOTO_RIGHT_EQUIP              = 0x00001000;          // 슈퍼 셀러 베너 사진 권한 중장비 4096
    public static final int     SUPER_PHOTO_RIGHT_EQUIP_PART         = 0x00002000;          // 슈퍼 셀러 베너 사진 권한 중장비 부품 8192
    public static final int     SUPER_PHOTO_RIGHT_PART               = 0x00004000;          // 슈퍼 셀러 베너 사진 권한 부품 16384
    public static final int     SUPER_PHOTO_RIGHT_MACHINERY          = 0x00008000;          // 슈퍼 셀러 베너 사진 권한 기계류 32768
    
    
    // Super Seller Banner PageID
    public static final String  SUPER_PAGEID_CAR_SEARCH              = "C010";           // 슈퍼 셀러 베너 페이지ID car search
    public static final String  SUPER_PAGEID_SALVAGE_SEARCH          = "C015";           // 슈퍼 셀러 베너 페이지ID salage search
    public static final String  SUPER_PAGEID_TRUCK_SEARCH            = "C020";           // 슈퍼 셀러 베너 페이지ID truck search
    public static final String  SUPER_PAGEID_BUS_SEARCH              = "C030";           // 슈퍼 셀러 베너 페이지ID bus search
    public static final String  SUPER_PAGEID_EQUIP_SEARCH            = "C040";           // 슈퍼 셀러 베너 페이지ID equip search
    public static final String  SUPER_PAGEID_EQUIP_PART_SEARCH       = "C045";           // 슈퍼 셀러 베너 페이지ID equip part search
    public static final String  SUPER_PAGEID_PART_SEARCH             = "C050";           // 슈퍼 셀러 베너 페이지ID part search
    public static final String  SUPER_PAGEID_MACHINERY_SEARCH        = "C060";           // 슈퍼 셀러 베너 페이지ID machinery search
    public static final String  SUPER_PAGEID_SEARCH                  = "C070";           // 슈퍼 셀러 베너 페이지ID 통합 검색
    public static final String  SUPER_PAGEID_LOCAL                   = "C080";           // 슈퍼 셀러 베너 페이지ID 로컬 페이지
    public static final String  SUPER_PAGEID_M_CAR_SEARCH            = "M010";           // 슈퍼 셀러 베너 페이지ID MOBILE car search
    public static final String  SUPER_PAGEID_M_TRUCK_SEARCH          = "M020";           // 슈퍼 셀러 베너 페이지ID MOBILE truck search
    public static final String  SUPER_PAGEID_M_BUS_SEARCH            = "M030";           // 슈퍼 셀러 베너 페이지ID MOBILE bus search
    public static final String  SUPER_PAGEID_M_EQUIP_SEARCH          = "M040";           // 슈퍼 셀러 베너 페이지ID MOBILE equip search
    public static final String  SUPER_PAGEID_M_PART_SEARCH           = "M050";           // 슈퍼 셀러 베너 페이지ID MOBILE part search
    public static final String  SUPER_PAGEID_M_MACHINERY_SEARCH      = "M060";           // 슈퍼 셀러 베너 페이지ID MOBILE machinery search
    public static final String  SUPER_PAGEID_M_SEARCH                = "M070";           // 슈퍼 셀러 베너 페이지ID MOBILE 통합 검색
    public static final String  SUPER_PAGEID_M_LOCAL                 = "M080";           // 슈퍼 셀러 베너 페이지ID 로컬 페이지
    
    // 리스팅 점수 
    public static final int  SORT_POINT_SUPER                   = 2800;        // 슈퍼 셀러 기본 점수
    public static final int  SORT_POINT_EXPERT                  = 2300;        // 엑스퍼트 기본 점수
    public static final int  SORT_POINT_POWER                   = 1800;        // 파워멤버 기본 점수
    public static final int  SORT_POINT_BIS                     = 800;        // BIS 설정시
    public static final int  SORT_POINT_BIN                     = 1800;       // BIN 설정시 1000
    public static final int  SORT_POINT_CR                      = 100;        // 컨디션 리포트 설정시
    public static final int  SORT_POINT_APP                     = 0;        // 앱 상품 등록 -- 100 -->0 임시로 수정
    public static final int  SORT_POINT_VIDEO                   = 100;        // 동영상 설정시
    public static final int  SORT_POINT_VERIFIED                = 100;        // Verified Stock 설정시
    public static final int  SORT_POINT_PRIME                   = 200;        // 프라임 멤버  +20
    public static final int  SORT_POINT_EXCLUSIVE               = 300;        // 전용 멤버 +300
    
    
    //관리자 > BIS> 예약관리 > 상세보기  문자 보내기 템플릿 코드 
    public static final String  BOOKING_SMS_TEMPLATE_KO_CONFIRM             = "C010";        // 예약화인 문자
    public static final String  BOOKING_SMS_TEMPLATE_KO_PAYMENT             = "C020";        // 입금확인 문자
    public static final String  BOOKING_SMS_TEMPLATE_KO_CI                  = "C030";        // CI 발행 문자
    public static final String  BOOKING_SMS_TEMPLATE_KO_DOCUMENT            = "C040";        // 서류확인 문자
    public static final String  BOOKING_SMS_TEMPLATE_KO_COMPLETE_PAYMENT    = "C050";        // 송금 완료 안내 문자 발송
    public static final String  BOOKING_SMS_TEMPLATE_KO_INVOICE             = "C060";        // 인보이스 발송
    public static final String  BOOKING_SMS_TEMPLATE_KO_PAYMENT_RECEIPT     = "C070";        // 송금 영수증 확인
    public static final String  BOOKING_SMS_TEMPLATE_KO_PAYMENT_CONFIRM     = "C080";        // 입금 확인 문자
    public static final String  BOOKING_SMS_TEMPLATE_KO_SELLER_PAYMNET      = "C090";        // 셀러 지급 완료
    public static final String  BOOKING_SMS_TEMPLATE_KO_SUN                 = "C100";        // 선적 완료 후 자동 문자
    public static final String  BOOKING_SMS_TEMPLATE_KO_SHORING             = "C110";        // 컨테이너 및 콘솔리데이션 쇼링 안내 문자
    public static final String  BOOKING_SMS_TEMPLATE_KO_REQUEST_DOCUMENT    = "C120";        // 컨테이너 서류요청 문자발송
    public static final String  BOOKING_SMS_TEMPLATE_KO_COMPLETE_DISPATCH   = "C130";        // 배차 신청 완료
    public static final String  BOOKING_SMS_TEMPLATE_KO_RORO_2ND_PUP        = "C140";        // RoRo 2차 발송
}
