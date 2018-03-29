(ns jarvis.components.header
  (:require [rum.core :as rum]
            [cljs-react-material-ui.rum :as ui]))

(def menu-items
  [{:label "Home"
    :route :home
    :link "/"
    :display-for :always}
   {:label "Dictionary"
    :route :dictionary
    :link "/dictionary"
    :display-for :always}
   {:label "Sign in"
    :route :login
    :link "/login"
    :display-for :non-logged}
   {:label "Sign up"
    :route :sign-up
    :link "/register"
    :display-for :non-logged}])

(rum/defc Menu < rum/static
  [curr-route close {:keys [label icon route link]}]
  [:a {:href (str "#" link)}
   (ui/menu-item
    {:disabled (= route curr-route) ;TODO add selected style
     :primary-text label
     :on-click close})])

(rum/defcs Header < (rum/local {:open? false :anchor nil})
  [state r route]
  (let [s (:rum/local state)
        close #(swap! s assoc :open? false)]
    [:.container
     (ui/app-bar
      {:title "J.A.R.V.I.S"
       :on-left-icon-button-touch-tap #(swap! s assoc :open? true :anchor (.-currentTarget %))})
     (ui/popover
      {:open (:open? @s)
       :anchor-el (:anchor @s)
       :on-request-close close}
      (ui/menu
       (map #(rum/with-key (Menu route close %) (:label %)) menu-items)))]))
