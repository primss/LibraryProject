package inc.primssware.mylibrary.iconify;

import com.joanzapata.iconify.Icon;

public enum Icons implements Icon {
    ic_call('\ue927'),
    ic_cake('\ue93e'),
    ic_check('\ue921'),
    ic_chevron_left('\ue934'),
    ic_chevron_up('\ue936'),
    ic_chevron_right('\ue933'),
    ic_chevron_down('\ue935'),
    ic_camera('\ue93a'),
    ic_cloud('\ue946'),
    ic_cloud_check('\ue943'),
    ic_cloud_download('\ue945'),
    ic_cloud_upload('\ue944'),
    ic_cog('\ue920'),
    ic_coffee('\ue912'),
    ic_coffee2('\ue913'),
    ic_compass('\ue90d'),
    ic_credit_card('\ue932'),
    ic_cross('\ue919'),
    ic_copyright('\ue92f'),
    ic_download('\ue92c'),
    ic_eye('\ue925'),
    ic_eye_blocked('\ue924'),
    ic_food('\ue900'),
    ic_help('\ue930'),
    ic_home('\ue941'),
    ic_ice_cream('\ue93d'),
    ic_image('\ue93b'),
    ic_images('\ue93c'),
    ic_info('\ue91a'),
    ic_key('\ue915'),
    ic_key_o('\ue918'),
    ic_legal('\ue91c'),
    ic_loading('\ue902'),
    ic_loading2('\ue903'),
    ic_loading3('\ue904'),
    ic_locate('\ue90c'),
    ic_location('\ue90e'),
    ic_location2('\ue90f'),
    ic_map('\ue90b'),
    ic_map_signs('\ue905'),
    ic_map_o('\ue90a'),
    ic_minus('\ue909'),
    ic_plus('\ue911'),
    ic_pig('\ue92e'),
    ic_pizza('\ue939'),
    ic_question('\ue931'),
    ic_redo('\ue91b'),
    ic_restaurant('\ue901'),
    ic_restaurant2('\ue917'),
    ic_restaurant_menu('\ue916'),
    ic_road('\ue93f'),
    ic_road2('\ue940'),
    ic_safe('\ue92d'),
    ic_save('\ue914'),
    ic_star_empty('\ue91f'),
    ic_star_full('\ue91e'),
    ic_sign_in('\ue937'),
    ic_sign_out('\ue938'),
    ic_trash('\ue926'),
    ic_undo('\ue91d'),
    ic_user('\ue908'),
    ic_user_add('\ue907'),
    ic_user_delete('\ue906'),
    ic_users('\ue910'),
    ic_users2('\ue947'),
    ic_website('\ue942'),
    ic_write_feather('\ue922'),
    ic_write_pencil('\ue923');

    char character;

    Icons(char character) {
        this.character = character;
    }

    @Override
    public String key() {
        return name().replace('_', '-');
    }

    @Override
    public char character() {
        return character;
    }
}
