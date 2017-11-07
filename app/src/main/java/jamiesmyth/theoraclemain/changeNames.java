package jamiesmyth.theoraclemain;

public class changeNames {

    public String changeName (String teamName) {

        switch (teamName) {
            case "AFC Bournemouth":
                teamName = "Bou";
                break;
            case "Arsenal FC":
                teamName = "Ars";
                break;
            case "Brighton & Hove Albion":
                teamName = "BHA";
                break;
            case "Burnley FC":
                teamName = "Bur";
                break;
            case "Crystal Palace FC":
                teamName = "Cry";
                break;
            case "Chelsea FC":
                teamName = "Che";
                break;
            case "Everton FC":
                teamName = "Eve";
                break;
            case "Huddersfield Town":
                teamName = "Hud";
                break;
            case "Leicester City FC":
                teamName = "Lei";
                break;
            case "Liverpool FC":
                teamName = "Liv";
                break;
            case "Manchester City FC":
                teamName = "MNC";
                break;
            case "Manchester United FC":
                teamName = "MNU";
                break;
            case "Newcastle United FC":
                teamName = "New";
                break;
            case "Stoke City FC":
                teamName = "Sto";
                break;
            case "Southampton FC":
                teamName = "Sou";
                break;
            case "Swansea City FC":
                teamName = "Swa";
                break;
            case "Tottenham Hotspur FC":
                teamName = "Tot";
                break;
            case "Watford FC":
                teamName = "Wat";
                break;
            case "West Bromwich Albion FC":
                teamName = "WBA";
                break;
            case "West Ham United FC":
                teamName = "WHU";
                break;
            /////////// CHAMPIONSHIP //////////
            case "Aston Villa FC":
                teamName = "Ast";
                break;
            case "Barnsley FC":
                teamName = "Bar";
                break;
            case "Birmingham City":
                teamName = "Bir";
                break;
            case "Bolton Wanderers FC":
                teamName = "Bol";
                break;
            case "Burton Albion FC":
                teamName = "Bur";
                break;
            case "Bristol City":
                teamName = "Bri";
                break;
            case "Brentford FC":
                teamName = "Bre";
                break;
            case "Cardiff City FC":
                teamName = "Car";
                break;
            case "Derby County":
                teamName = "Der";
                break;
            case "Fulham FC":
                teamName = "Ful";
                break;
            case "Hull City FC":
                teamName = "Hul";
                break;
            case "Ipswich Town":
                teamName = "Ips";
                break;
            case "Leeds United":
                teamName = "Lee";
                break;
            case "Norwich City FC":
                teamName = "Nor";
                break;
            case "Nottingham Forest":
                teamName = "Not";
                break;
            case "Middlesbrough FC":
                teamName = "Mid";
                break;
            case "Millwall FC":
                teamName = "Mil";
                break;
            case "Preston North End":
                teamName = "PNE";
                break;
            case "Queens Park Rangers":
                teamName = "QPR";
                break;
            case "Reading":
                teamName = "Rea";
                break;
            case "Sheffield Wednesday":
                teamName = "Wed";
                break;
            case "Sheffield United FC":
                teamName = "Uni";
                break;
            case "Sunderland AFC":
                teamName = "Sun";
                break;
            case "Wolverhampton Wanderers FC":
                teamName = "Wol";
                break;
            ////////// LEAGUE ONE //////////
            case "MillwallFC":
                teamName = "Mil";
                break;
            default:
                teamName = "N/A";
                break;
        }

        return teamName;
    }
}
