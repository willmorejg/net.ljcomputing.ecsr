(function(e){if(typeof define==="function"&&define.amd){define(["knockout","jquery"],e)}else{e(window.ko,window.jQuery)}})(function(e,t){e.bindingHandlers.editable={init:function(n,r,i,s,o){var u=t(n),a=r(),f=i(),l=f.editableOptions||{};l.value=e.utils.unwrapObservable(a);if(!l.name){t.each(o.$data,function(e,t){if(t==a){l.name=e;return false}})}if(!l.validate&&a.isValid){l.validate=function(t){var n=a();a(t);var r=a.isValid()?null:e.utils.unwrapObservable(a.error);a(n);return r}}if((l.type==="select"||l.type==="checklist"||l.type==="typeahead")&&!l.source&&l.options){if(l.optionsCaption)l.prepend=l.optionsCaption;function c(e,t,n){var r=typeof t;if(r=="function")return t(e);else if(r=="string")return e[t];else return n}l.source=function(){return e.utils.arrayMap(e.utils.unwrapObservable(l.options),function(t){var n=c(t,l.optionsValue,t);var r=c(t,l.optionsText,n);return{value:e.utils.unwrapObservable(n),text:e.utils.unwrapObservable(r)}})}}if(l.visible&&e.isObservable(l.visible)){l.toggle="manual"}var h=u.editable(l);if(e.isObservable(a)){h.on("save.ko",function(e,t){a(t.newValue)})}if(l.save){h.on("save",l.save)}e.computed({read:function(){var t=e.utils.unwrapObservable(r());if(t===null)t="";h.editable("setValue",t,true)},owner:this,disposeWhenNodeIsRemoved:n});if(l.visible&&e.isObservable(l.visible)){e.computed({read:function(){var t=e.utils.unwrapObservable(l.visible());if(t)h.editable("show")},owner:this,disposeWhenNodeIsRemoved:n});h.on("hidden.ko",function(e,t){l.visible(false)})}}}})